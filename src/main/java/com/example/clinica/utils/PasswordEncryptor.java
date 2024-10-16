package com.example.clinica.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
