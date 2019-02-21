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
public class THexConst extends AToken {

    private final int decimalValue;

    public THexConst(int val) {
        decimalValue = val;
    }
    public int getIntValue(){
        return decimalValue;
    }
    
    @Override
    public String getDescription() {
        return String.format("Hexadecimal constant = %d", decimalValue);
    }

}
