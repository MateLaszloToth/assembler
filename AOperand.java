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
abstract public class AOperand {

    abstract public String generateListing();

    abstract public int getIntValue(); //return the value of PIntArg or PHexArg

    abstract public String generateCode();
}
