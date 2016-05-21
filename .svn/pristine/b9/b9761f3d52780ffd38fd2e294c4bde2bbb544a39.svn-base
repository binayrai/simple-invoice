package com.itexpertnepal.simpleinvoice.utilities;

import java.util.Random;

/**
 *
 * @author binay
 */
public class StringUtls {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigkhlmnopqrstuvwxyz";
    static Random rnd = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String randomNumber(int len) {
        String numberArray = "0123456789012345678901234567890123456789012345678901234567890123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(numberArray.charAt(rnd.nextInt(numberArray.length())));
        }
        return sb.toString();
    }

}
