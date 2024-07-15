package com.example.demo.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Test;
import com.example.demo.repository.UserRepository;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //IMPLEMENTER PBKDF2

    public Test getUser(Test user){
        Optional<Test> userFromDb = userRepository.findById(user.getId());
    if(userFromDb.isEmpty() || !userFromDb.isPresent() ){
            throw new RuntimeException();
        }
        return userFromDb.get();
    }

    public List<Test> getAllUsers(){
        List<Test> allUsers = userRepository.findAll();
        return allUsers;
    }


    public void createUser(Test user){
        setPassword(user.getPassword(), user);
        userRepository.save(user);
    }

    public Test editUser(Test user){
        Optional<Test> existingUser = userRepository.findById(user.getId());
        if(existingUser.isEmpty()){
         throw new RuntimeException();
        }
        Test newUser = new Test();
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

    public void deleteUser(Test user){
        Long userId = user.getId();
        userRepository.delete(user);
    }

    public void changePassword(Test user){
        setPassword(user.getPassword(), user);
        userRepository.save(user);
    }

    public void setPassword(String password, Test user) {
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
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }


}




