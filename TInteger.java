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
public class TInteger extends AToken {

    private final int intValue;

    public TInteger(int val) {
        intValue = val;
    }
    
    public int getIntValue(){
        return intValue;
    }
    
@Override
    public String getDescription() {
        return String.format("Integer = %d", intValue);
    }
}
