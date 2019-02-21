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
public class EmptyInstr extends ACode {
    // For an empty source line.

    @Override
    public String generateListing() {
        return "\n";
    }

    @Override
    public String generateCode() {
        return "";
    }
}
