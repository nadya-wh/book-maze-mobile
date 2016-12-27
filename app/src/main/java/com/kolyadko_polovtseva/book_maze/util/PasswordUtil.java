package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nadzeya_Polautsava on 10/22/2016.
 */

public class PasswordUtil {
    public static final String ALGORITHM = "MD5";
    public static final String CHARSET_NAME = "UTF-8";
    public static final String PASSWORD_UTIL_TAG = "PasswordUtil";

    public static String encryptPassword(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = messageDigest.digest(password.getBytes(CHARSET_NAME));
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Log.d(PASSWORD_UTIL_TAG, "encrypt password", e);
        }
        return password;
    }
}
