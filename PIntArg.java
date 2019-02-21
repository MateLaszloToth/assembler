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
public class PIntArg extends AOperand {

    private final int value;

    public PIntArg(int digit) {
        value = digit;
    }

    @Override
    public int getIntValue() { // use at PDotBlock
        return value;
    }

    @Override
    public String generateListing() {
        return String.format("%d", value);
    }

    @Override
    public String generateCode() {
        if (value >= 0) {
            int first = value / 256;
            int second = value % 256;
            return String.format("%02X %02X", first, second);
        } else {
            int tempVal = -value;
            int output = 0;
            int range = 32767; // the range from 0 to 7FFF
            output = 2 * range - tempVal + 2;
            int first = output / 256;
            int second = output % 256;
            return String.format("%02X %02X", first, second);
        }
    }
}
