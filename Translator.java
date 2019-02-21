/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob0719;

import java.util.ArrayList;

/**
 *
 * @author matetoth
 */
public class Translator {

    private final InBuffer b;
    private Tokenizer t;
    private ACode aCode;

    public Translator(InBuffer inBuffer) {
        b = inBuffer;
    }

    // Sets aCode and returns boolean true if end statement is processed.
    private boolean parseLine() {
        boolean terminate = false;
        AOperand localOp = new PIntArg(0);
        Mnemon localMnemon = Mnemon.M_STOP; // Compiler requires useless initialization.
        DotCommand localDotCommand = DotCommand.DC_END; // Compiler requires useless initialization.
        PAddressingMode localAddressing = PAddressingMode.PAM_EMPTY;
        AToken aToken;
        aCode = new EmptyInstr();
        ParseState state = ParseState.PS_START;
        do {
            aToken = t.getToken();
            switch (state) {
                case PS_START:
                    if (aToken instanceof TIdentifier) {
                        TIdentifier localTIdentifier = (TIdentifier) aToken;
                        String tempStr = localTIdentifier.getStringValue();
                        if (Maps.unaryMnemonTable.containsKey(tempStr.toUpperCase())) {
                            localMnemon = Maps.unaryMnemonTable.get(tempStr.toUpperCase());
                            aCode = new UnaryInst(localMnemon);
                            state = ParseState.PS_UNARYINST;
                        } else if (Maps.nonUnaryMnemonTable.containsKey(tempStr.toUpperCase())) {
                            localMnemon = Maps.nonUnaryMnemonTable.get(tempStr.toUpperCase());
                            state = ParseState.PS_INSTSPEC;
                        } else {
                            aCode = new Error("Illegal mnemonic.");
                        }
                    } else if (aToken instanceof TDotCommand) {
                        TDotCommand localTDotCommand = (TDotCommand) aToken;
                        String tempStr = localTDotCommand.getStringValue();
                        if (Maps.dotCommand1Table.containsKey(tempStr.toUpperCase())) {
                            localDotCommand = Maps.dotCommand1Table.get(tempStr.toUpperCase());
                            state = ParseState.PS_PDotBlock;
                        } else if (Maps.dotCommand2Table.containsKey(tempStr.toUpperCase())) {
                            localDotCommand = Maps.dotCommand2Table.get(tempStr.toUpperCase());
                            aCode = new PDotEnd(localDotCommand);
                            terminate = localDotCommand == DotCommand.DC_END;
                            state = ParseState.PS_PDotEnd;
                        } else {
                            aCode = new Error("Illegal dot command.");
                        }
                    } else if (aToken instanceof TEmpty) {
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Line must begin with an identifier or dot command.");
                    }
                    break;

                case PS_UNARYINST:
                    if (aToken instanceof TEmpty) {

                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Illegal trailing character.");
                    }
                    break;
                case PS_INSTSPEC:
                    boolean isTInteger = aToken instanceof TInteger;
                    boolean isTHexConst = aToken instanceof THexConst;
                    if (isTInteger || isTHexConst) {
                        if (isTInteger) {
                            TInteger localInt = (TInteger) aToken;
                            localOp = new PIntArg(localInt.getIntValue());
                        } else {
                            THexConst localHex = (THexConst) aToken;
                            localOp = new PHexArg(localHex.getIntValue());
                        }
                        state = ParseState.PS_OPERANDSPEC;
                    } else {
                        aCode = new Error("Illegal integer or hexadecimal value.");
                    }
                    break;
                case PS_OPERANDSPEC:
                    if (aToken instanceof TEmpty) {
                        if (Util.branchMnemon(localMnemon)) {
                            localAddressing = Maps.addressingModeTable.get("");
                            aCode = new NonUnaryInstr(localMnemon, localOp, localAddressing);
                            state = ParseState.PS_FINISH;
                        } else {
                            aCode = new Error("Mnemonic needs an address.");
                        }
                    } else if (aToken instanceof TAddressingMode) {
                        TAddressingMode localTAddress = (TAddressingMode) aToken;
                        String tempStr = localTAddress.getStringValue();
                        localAddressing = Maps.addressingModeTable.get(tempStr.toLowerCase());
                        if (Util.branchMnemon(localMnemon) && (localAddressing == PAddressingMode.PAM_I)) {
                            localAddressing = Maps.addressingModeTable.get("");
                            aCode = new NonUnaryInstr(localMnemon, localOp, localAddressing);
                            state = ParseState.PS_FINISH;
                        } else if ((Util.branchMnemon(localMnemon))
                                && (localAddressing == PAddressingMode.PAM_X)) {
                            aCode = new NonUnaryInstr(localMnemon, localOp, localAddressing);
                            state = ParseState.PS_NONUNARYINST;
                        } else if (Util.noImm(localMnemon) && (localAddressing == PAddressingMode.PAM_I)) {
                            aCode = new Error("Invalid addressing mode.");
                        } else if (!(Util.branchMnemon(localMnemon))
                                && Maps.addressingModeTable.containsKey(tempStr.toLowerCase())) {
                            aCode = new NonUnaryInstr(localMnemon, localOp, localAddressing);
                            state = ParseState.PS_NONUNARYINST;
                        } else {
                            aCode = new Error("Invalid addressing mode.");
                        }
                    } else {
                        aCode = new Error("Invalid mnemonic.");
                    }
                    break;
                case PS_NONUNARYINST:
                    if (aToken instanceof TEmpty) {

                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Illegal trailing character.");
                    }

                    break;
                case PS_PDotBlock:
                    if (aToken instanceof TInteger) {
                        TInteger localInt = (TInteger) aToken;
                        if (localInt.getIntValue() >= 0) {
                            localOp = new PIntArg(localInt.getIntValue());
                            aCode = new PDotBlock(localDotCommand, localOp);
                            state = ParseState.PS_DATASPACE;
                        } else {
                            aCode = new Error("Illegal integer or hexadecimal value.");
                        }
                    } else if (aToken instanceof THexConst) {
                        THexConst localHex = (THexConst) aToken;
                        localOp = new PHexArg(localHex.getIntValue());
                        aCode = new PDotBlock(localDotCommand, localOp);
                        state = ParseState.PS_DATASPACE;
                    } else {
                        aCode = new Error("Illegal integer or hexadecimal value.");
                    }
                    break;
                case PS_DATASPACE:
                    if (aToken instanceof TEmpty) {
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Illegal trailing character.");
                    }
                    break;
                case PS_PDotEnd:
                    if (aToken instanceof TEmpty) {
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Illegal trailing character.");
                    }
                    break;
            }

        } while (state != ParseState.PS_FINISH && !(aCode instanceof Error));
        return terminate;
    }

    public void translate() {
        ArrayList<ACode> codeTable = new ArrayList<>();
        int numErrors = 0;
        t = new Tokenizer(b);
        boolean terminateWithEnd = false;
        b.getLine();
        while (b.inputRemains() && !terminateWithEnd) {
            terminateWithEnd = parseLine(); // Sets aCode and returns boolean.
            codeTable.add(aCode);
            if (aCode instanceof Error) {
                numErrors++;
            }
            b.getLine();
        }
        if (!terminateWithEnd) {
            aCode = new Error("Missing \"end\" sentinel.");
            codeTable.add(aCode);
            numErrors++;
        }
        if (numErrors == 0) {
            System.out.printf("Object code:\n");
            for (int i = 0; i < codeTable.size(); i++) {
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        }
        if (numErrors == 1) {
            System.out.printf("One error was detected.\n");
        } else if (numErrors > 1) {
            System.out.printf("%d errors were detected.\n", numErrors);
        }

        System.out.printf("\nProgram listing:\n");
        for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateListing());
        }
    }
}
