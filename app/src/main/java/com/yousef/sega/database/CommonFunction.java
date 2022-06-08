package com.yousef.sega.database;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CommonFunction {

    public String encryption(String text) throws Exception{
        String password="YousefShaabanSaad";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes=password.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes, 0, bytes.length);
        byte[] key =messageDigest.digest();

        SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        byte[] encryptValue=cipher.doFinal(text.getBytes());

        return Base64.encodeToString(encryptValue, Base64.DEFAULT);
    }

    public String decryption(String text) throws Exception{
        String password="YousefShaabanSaad";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes=password.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes, 0, bytes.length);
        byte[] key =messageDigest.digest();

        SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec);

        byte[] decryptValue=Base64.decode(text, Base64.DEFAULT);
        byte[] decValue=cipher.doFinal(decryptValue);

        return new String(decValue);
    }
}
