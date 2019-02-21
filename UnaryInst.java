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
public class UnaryInst extends ACode {

    private final Mnemon mnemonic;

    public UnaryInst(Mnemon mn) {
        mnemonic = mn;
    }

    @Override
    public String generateListing() {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    }

    @Override
    public String generateCode() {
        return String.format("%02X\n", Maps.mnemonIntTable.get(mnemonic));
    }
}
