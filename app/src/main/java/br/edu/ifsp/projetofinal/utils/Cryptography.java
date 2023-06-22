package br.edu.ifsp.projetofinal.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Cryptography {
    public static String encrypt(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String md5Hash = hexString.toString();
            return md5Hash;
        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", "Erro na criptografia");
        }
        return null;
    }
}
