/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob0719;

import java.lang.*;

/**
 *
 * @author matetoth
 */
public class Util {

    public static boolean isLetter(char ch) {
        ch = Character.toLowerCase(ch);
        return ('a' <= ch && ch <= 'z');
    }

    public static boolean isDigit(char ch) {
        return ('0' <= ch) && (ch <= '9');
    }

    public static boolean isHexAlpha(char ch) {
        ch = Character.toLowerCase(ch);
        return ('a' <= ch && ch <= 'f');
    }

    public static int decVal(char hex) {
        int dec = 0;
        hex = Character.toLowerCase(hex);
        if (isHexAlpha(hex)) {
            dec = hex - 'a' + 10;
        } else if (isDigit(hex)) {
            dec = hex - '0';
        } else {
            System.out.printf("Not proper value");
        }
        return dec;
    }

    public static boolean branchMnemon(Mnemon mn) {
        return mn == Mnemon.M_BR || mn == Mnemon.M_BREQ
                || mn == Mnemon.M_BRLT || mn == Mnemon.M_BRLE;
    }

    public static boolean noImm(Mnemon m) {
        return m == Mnemon.M_DECI || m == Mnemon.M_STWA;
    }

}
