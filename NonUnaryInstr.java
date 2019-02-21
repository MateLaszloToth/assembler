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
public class NonUnaryInstr extends ACode {

    private final Mnemon mnemonic;
    private final AOperand operand;
    private final PAddressingMode addressing;

    public NonUnaryInstr(Mnemon mn, AOperand op, PAddressingMode add) {
        mnemonic = mn;
        operand = op;
        addressing = add;
    }

    @Override
    public String generateListing() {
        if (mnemonic == Mnemon.M_BR) {
            return String.format("%s      %s%s \n",
                    Maps.mnemonStringTable.get(mnemonic),
                    operand.generateListing(),
                    Maps.addressingModeStringTable.get(addressing));
        } else {
            return String.format("%s    %s%s \n",
                    Maps.mnemonStringTable.get(mnemonic),
                    operand.generateListing(),
                    Maps.addressingModeStringTable.get(addressing));
        }
    }

    @Override
    public String generateCode() {
        int ordinalValue = 0;
        if (Util.branchMnemon(mnemonic)) {
            if (addressing == PAddressingMode.PAM_X) {
                ordinalValue = 1;
            }
            return String.format("%02X %2s\n", Maps.mnemonIntTable.get(mnemonic)
                    + ordinalValue, operand.generateCode());
        } else {
            return String.format("%02X %2s\n", Maps.mnemonIntTable.get(mnemonic)
                    + addressing.ordinal(), operand.generateCode());
        }
    }
}
