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
public class Error extends ACode {

    private final String errorMessage;

    public Error(String errMessage) {
        errorMessage = errMessage;
    }

    @Override
    public String generateListing() {
        return "ERROR: " + errorMessage + "\n";
    }

    @Override
    public String generateCode() {
        return "";
    }
}
