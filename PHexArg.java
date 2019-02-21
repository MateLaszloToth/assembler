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
public class PHexArg extends AOperand {

    private final int value;

    public PHexArg(int hex) {
        value = hex;
    }

    public int getIntValue() { // use at PDotBlock
        return value;
    }

    public String generateListing() {
        return String.format("0x%04X", value);
    }

    public String generateCode() {
        int first = value / 256;
        int second = value % 256;
        return String.format("%02X %02X", first, second);
    }
}
