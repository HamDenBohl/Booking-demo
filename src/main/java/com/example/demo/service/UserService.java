package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

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
        // Parameters: iterations = 3, memory = 64MB, parallelism = 2
        int iterations = 3;
        int memory = 65536; // 64 MB
        int parallelism = 2;
        // Hash the password with Argon2
        String hashedPassword = argon2.hash(iterations, memory, parallelism, password);

        user.setHashedPassword(hashedPassword);
    }

    public boolean checkPassword(String password, User user) {
        String hashedPassword = user.getHashedPassword();

        // Verify the password with Argon2
        return argon2.verify(hashedPassword, password);
    }
}




