package com.example.demo.model;
import com.example.demo.UserType;
import jakarta.persistence.*;
import java.security.SecureRandom;
import java.util.Base64;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer phonenumber;
    private UserType userType;
    private String email;
    private String salt;
    private String hashedPassword;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //IMPLEMENTER PBKDF2

    public void setPassword(String password) {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        this.salt = Base64.getEncoder().encodeToString(saltBytes);

        // Hash the password with the salt
        String hashedPassword = hashPassword(password);
        this.hashedPassword = hashedPassword;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }

}