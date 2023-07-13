package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.entities.User;
import com.specscapstone.bookReviewApp.dtos.UserDto;
import com.specscapstone.bookReviewApp.repositories.UserRepository;
import com.specscapstone.bookReviewApp.configuration.UserSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // USER REGISTRATION
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();

        // Check if username already exists
        Optional<User> existingUsername = userRepository.findByUsername(userDto.getUsername());
        if (existingUsername.isPresent()) {
            response.add("Username already exists. Please choose a different username.");
        } else {
            // Check if email already exists
            Optional<User> existingEmail = userRepository.findByEmail(userDto.getEmail());
            if (existingEmail.isPresent()) {
                response.add("Email already exists. Please use a different email address.");
            } else {
                User user = new User(userDto);
                // Hash password
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                userRepository.saveAndFlush(user);
                response.add("User registered successfully");
            }
        }
        return response;
    }

    // USER LOGIN
    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();

        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if (userOptional.isPresent()) {
            // Check password matches hashed password
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add("User login successful");
                // Get the username
                String username = userOptional.get().getUsername();
                // Create User Session
                storeUserSession(userOptional.get(), username);
            } else {
                response.add("Email or Password is incorrect");
            }
        } else {
            response.add("Email or Password is incorrect");
        }

        return response;
    }

    // Create User Session
    private void storeUserSession(User user, String username) {
        UserSession userSession = new UserSession(user, username);
        SecurityContextHolder.getContext().setAuthentication((Authentication) userSession);
    }

}
