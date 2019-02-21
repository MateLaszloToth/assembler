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
public class PDotEnd extends ACode {

    private final DotCommand dotEnd;

    public PDotEnd(DotCommand end) {
        dotEnd = end;
    }

    public String generateListing() {
        return Maps.dotCommandStringTable.get(dotEnd) + "\n\n";
    }

    public String generateCode() {
        return "zz\n";
    }

}
