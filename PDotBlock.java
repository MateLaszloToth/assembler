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
public class PDotBlock extends ACode {

    private final DotCommand dotCommand;
    private final AOperand operand;

    public PDotBlock(DotCommand dc, AOperand op) {
        dotCommand = dc;
        operand = op;

    }

    public String generateListing() {
        return String.format("%s  %s", Maps.dotCommandStringTable.get(dotCommand),
                operand.generateListing() + "\n");
    }

    public String generateCode() {
        int value = operand.getIntValue();
        String stringOutput = "00";
        if (value >= 1) {
            for (int j = 1; j < value; j++) { // 1 because stringOutput was initialized to "00"
                stringOutput += " 00";
            }
            stringOutput += "\n";
            return stringOutput;
        } else {
            return "";
        }
    }
}
