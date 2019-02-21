/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob0719;

/**
 *
 * @author matetoth
 */
public class Tokenizer {

    private final InBuffer b;

    public Tokenizer(InBuffer inBuffer) {
        b = inBuffer;
    }

    public AToken getToken() {
        char nextChar;
        StringBuffer localStringValue = new StringBuffer("");
        int localIntValue = 0;
        int sign = +1;
        AToken aToken = new TEmpty();
        State state = State.LS_START;
        do {
            nextChar = b.advanceInput();
            switch (state) {
                case LS_START:
                    if (nextChar == '0') {
                        state = State.LS_INT1;
                    } else if (Util.isDigit(nextChar)) {
                        localIntValue = Util.decVal(nextChar);
                        state = State.LS_INT2;
                    } else if (nextChar == '+') {
                        state = State.LS_SIGN;
                    } else if (nextChar == '-') {
                        sign = -1;
                        state = State.LS_SIGN;
                    } else if (Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                        state = State.LS_IDENT;
                    } else if (nextChar == '.') {
                        state = State.LS_DOT1;
                    } else if (nextChar == ',') {
                        state = State.LS_ADDR1;
                    } else if (nextChar == '\n') {
                        state = State.LS_STOP;
                    } else if (nextChar != ' ') {
                        aToken = new TInvalid();
                    }
                    break;

                case LS_INT1:
                    if ((nextChar == 'x') || (nextChar == 'X')) {
                        state = State.LS_HEX1;
                    } else if (Util.isDigit(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = State.LS_INT2;
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(localIntValue);
                        state = State.LS_STOP;
                    }
                    break;

                case LS_HEX1:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = Util.decVal(nextChar);
                        state = State.LS_HEX2;
                    } else if (Util.isHexAlpha(nextChar)) {
                        localIntValue = Util.decVal(nextChar);
                        state = State.LS_HEX2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;

                case LS_HEX2:
                    if (Util.isDigit(nextChar) || Util.isHexAlpha(nextChar)) {
                        if (16 * localIntValue + Util.decVal(nextChar) > 65535) {
                            aToken = new TInvalid();
                        } else {
                            localIntValue = 16 * localIntValue + Util.decVal(nextChar);
                        }
                    } else {
                        b.backUpInput();
                        aToken = new THexConst(localIntValue);
                        state = State.LS_STOP;
                    }
                    break;

                case LS_INT2:
                    if (Util.isDigit(nextChar)) {
                        if (sign == 1) {
                            if ((10 * localIntValue + Util.decVal(nextChar)) > 65535) {
                                aToken = new TInvalid();
                            } else {
                                localIntValue = 10 * localIntValue + Util.decVal(nextChar);
                            }
                        } else if ((10 * localIntValue + Util.decVal(nextChar)) > 32768) {
                            aToken = new TInvalid();
                        } else {
                            localIntValue = 10 * localIntValue + Util.decVal(nextChar);
                        }
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = State.LS_STOP;
                    }
                    break;

                case LS_SIGN:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = Util.decVal(nextChar);
                        state = State.LS_INT2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;

                case LS_IDENT:
                    if (Util.isDigit(nextChar) || Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = State.LS_STOP;
                    }
                    break;

                case LS_DOT1:
                    if (Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                        state = State.LS_DOT2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;

                case LS_DOT2:
                    if (Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TDotCommand(localStringValue);
                        state = State.LS_STOP;
                    }
                    break;

                case LS_ADDR1:
                    if (Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                        state = State.LS_ADDR2;
                    } else if (nextChar != ' ') {
                        aToken = new TInvalid();
                    }
                    break;

                case LS_ADDR2:
                    if (Util.isLetter(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TAddressingMode(localStringValue);
                        state = State.LS_STOP;
                    }
                    break;
            }

        } while ((state != State.LS_STOP) && !(aToken instanceof TInvalid));

        return aToken;
    }
}
