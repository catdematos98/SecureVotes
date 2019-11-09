package com.example.securevotes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHelper {

    public static String applySHA256(String inputString){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(inputString.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for( int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
