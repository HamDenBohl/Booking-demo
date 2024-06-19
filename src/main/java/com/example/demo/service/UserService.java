package com.example.demo.service;

import java.security.SecureRandom;
import java.util.Base64;
import org.mindrot.jbcrypt.BCrypt;
public class UserService {

    //IMPLEMENTER PBKDF2

    public void setPassword(String password) {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);

        // Hash the password with the salt
        String hashedPassword = hashPassword(password);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }
}
