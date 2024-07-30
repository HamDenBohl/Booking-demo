package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //IMPLEMENTER PBKDF2

    public User getUser(User user) {
        Optional<User> userFromDb = userRepository.findById(user.getId());
        if (userFromDb.isEmpty() || !userFromDb.isPresent()) {
            throw new RuntimeException();
        }
        return userFromDb.get();
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }


    public void createUser(User user) {
        setPassword(user.getPassword(), user);
        userRepository.save(user);
    }

    public User editUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) {
            throw new RuntimeException();
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUserType(user.getUserType());
        newUser.setInitials(user.getInitials());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhonenumber(user.getPhonenumber());
        newUser.setId(existingUser.get().getId());
        newUser.setSalt(existingUser.get().getSalt());
        newUser.setHashedPassword(existingUser.get().getHashedPassword());
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteUser(User user) {
        Long userId = user.getId();
        userRepository.delete(user);
    }

    public void changePassword(User user) {
        setPassword(user.getPassword(), user);
        userRepository.save(user);
    }

    public void setPassword(String password, User user) {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);

        // Hash the password with the salt
        String hashedPassword = hashPassword(password);

        user.setSalt(salt);
        user.setHashedPassword(hashedPassword);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }


}




