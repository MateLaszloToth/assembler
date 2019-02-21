/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob0719;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matetoth
 */
public final class Maps {

    public static final Map<String, Mnemon> unaryMnemonTable;
    public static final Map<String, Mnemon> nonUnaryMnemonTable;
    public static final Map<Mnemon, String> mnemonStringTable;
    public static final Map<String, DotCommand> dotCommand1Table;
    public static final Map<String, DotCommand> dotCommand2Table;
    public static final Map<DotCommand, String> dotCommandStringTable;
    public static final Map<String, PAddressingMode> addressingModeTable;
    public static final Map<PAddressingMode, String> addressingModeStringTable;
    public static final Map<Mnemon, Integer> mnemonIntTable;

    static {
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("STOP", Mnemon.M_STOP);
        unaryMnemonTable.put("ASLA", Mnemon.M_ASLA);
        unaryMnemonTable.put("ASRA", Mnemon.M_ASRA);

        nonUnaryMnemonTable = new HashMap<>();
        nonUnaryMnemonTable.put("BR", Mnemon.M_BR);
        nonUnaryMnemonTable.put("BRLT", Mnemon.M_BRLT);
        nonUnaryMnemonTable.put("BREQ", Mnemon.M_BREQ);
        nonUnaryMnemonTable.put("BRLE", Mnemon.M_BRLE);
        nonUnaryMnemonTable.put("CPWA", Mnemon.M_CPWA);
        nonUnaryMnemonTable.put("DECI", Mnemon.M_DECI);
        nonUnaryMnemonTable.put("DECO", Mnemon.M_DECO);
        nonUnaryMnemonTable.put("ADDA", Mnemon.M_ADDA);
        nonUnaryMnemonTable.put("SUBA", Mnemon.M_SUBA);
        nonUnaryMnemonTable.put("STWA", Mnemon.M_STWA);
        nonUnaryMnemonTable.put("LDWA", Mnemon.M_LDWA);

        mnemonStringTable = new EnumMap<>(Mnemon.class);
        mnemonStringTable.put(Mnemon.M_BR, "BR");
        mnemonStringTable.put(Mnemon.M_BRLT, "BRLT");
        mnemonStringTable.put(Mnemon.M_BREQ, "BREQ");
        mnemonStringTable.put(Mnemon.M_BRLE, "BRLE");
        mnemonStringTable.put(Mnemon.M_CPWA, "CPWA");
        mnemonStringTable.put(Mnemon.M_DECI, "DECI");
        mnemonStringTable.put(Mnemon.M_DECO, "DECO");
        mnemonStringTable.put(Mnemon.M_ADDA, "ADDA");
        mnemonStringTable.put(Mnemon.M_SUBA, "SUBA");
        mnemonStringTable.put(Mnemon.M_STWA, "STWA");
        mnemonStringTable.put(Mnemon.M_LDWA, "LDWA");
        mnemonStringTable.put(Mnemon.M_STOP, "STOP");
        mnemonStringTable.put(Mnemon.M_ASLA, "ASLA");
        mnemonStringTable.put(Mnemon.M_ASRA, "ASRA");

        dotCommand1Table = new HashMap<>();
        dotCommand1Table.put("BLOCK", DotCommand.DC_BLOCK);

        dotCommand2Table = new HashMap<>();
        dotCommand2Table.put("END", DotCommand.DC_END);

        dotCommandStringTable = new EnumMap<>(DotCommand.class);
        dotCommandStringTable.put(DotCommand.DC_BLOCK, ".BLOCK");
        dotCommandStringTable.put(DotCommand.DC_END, ".END");

        addressingModeTable = new HashMap<>();
        addressingModeTable.put("i", PAddressingMode.PAM_I);
        addressingModeTable.put("d", PAddressingMode.PAM_D);
        addressingModeTable.put("n", PAddressingMode.PAM_N);
        addressingModeTable.put("s", PAddressingMode.PAM_S);
        addressingModeTable.put("sf", PAddressingMode.PAM_SF);
        addressingModeTable.put("x", PAddressingMode.PAM_X);
        addressingModeTable.put("sx", PAddressingMode.PAM_SX);
        addressingModeTable.put("sfx", PAddressingMode.PAM_SFX);
        addressingModeTable.put("", PAddressingMode.PAM_EMPTY);

        addressingModeStringTable = new EnumMap<>(PAddressingMode.class);
        addressingModeStringTable.put(PAddressingMode.PAM_I, ",i");
        addressingModeStringTable.put(PAddressingMode.PAM_D, ",d");
        addressingModeStringTable.put(PAddressingMode.PAM_N, ",n");
        addressingModeStringTable.put(PAddressingMode.PAM_S, ",s");
        addressingModeStringTable.put(PAddressingMode.PAM_SF, ",sf");
        addressingModeStringTable.put(PAddressingMode.PAM_X, ",x");
        addressingModeStringTable.put(PAddressingMode.PAM_SX, ",sx");
        addressingModeStringTable.put(PAddressingMode.PAM_SFX, ",sfx");
        addressingModeStringTable.put(PAddressingMode.PAM_EMPTY, "");

        mnemonIntTable = new EnumMap<>(Mnemon.class);
        mnemonIntTable.put(Mnemon.M_STOP, 0);
        mnemonIntTable.put(Mnemon.M_ASLA, 10);
        mnemonIntTable.put(Mnemon.M_ASRA, 12);
        mnemonIntTable.put(Mnemon.M_BR,18);
        mnemonIntTable.put(Mnemon.M_BRLT,22);
        mnemonIntTable.put(Mnemon.M_BREQ, 24);
        mnemonIntTable.put(Mnemon.M_BRLE, 20);
        mnemonIntTable.put(Mnemon.M_CPWA, 160);
        mnemonIntTable.put(Mnemon.M_DECI, 48);
        mnemonIntTable.put(Mnemon.M_DECO, 56);
        mnemonIntTable.put(Mnemon.M_ADDA, 96);
        mnemonIntTable.put(Mnemon.M_SUBA, 120);
        mnemonIntTable.put(Mnemon.M_STWA, 224);
        mnemonIntTable.put(Mnemon.M_LDWA, 192);

    }
}
