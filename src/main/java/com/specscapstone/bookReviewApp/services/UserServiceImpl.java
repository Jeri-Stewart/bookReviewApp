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
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        log.debug("Adding a new user: {}", userDto.getUsername());
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
                userRepository.save(user);
                response.add("User registered successfully");
            }
        }
        return response;
    }

    @Override
    public List<String> userLogin(String username, String password) {
        List<String> response = new ArrayList<>();

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            // Check password matches hashed password
            if (passwordEncoder.matches(password, userOptional.get().getPassword())) {
                response.add("User login successful");
                // Get the username
                String retrievedUsername = userOptional.get().getUsername();
                // Create User Session
                storeUserSession(userOptional.get(), retrievedUsername);
            } else {
                response.add("Username or Password is incorrect");
            }
        } else {
            response.add("Username or Password is incorrect");
        }

        return response;
    }

    private void storeUserSession(User user, String username) {
        UserSession userSession = new UserSession(user, username);
        SecurityContextHolder.getContext().setAuthentication((Authentication) userSession);
    }
}

