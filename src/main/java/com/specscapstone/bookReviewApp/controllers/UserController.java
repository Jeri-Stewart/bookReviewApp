package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.UserDto;
import com.specscapstone.bookReviewApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // To register a new user
    @PostMapping("/register-user")
    public List<String> userRegister(@RequestBody UserDto userDto) {
        logger.debug("Received UserDto: {}", userDto.toString());
        List<String> response = userService.addUser(userDto);
        return response;
    }

    // To log in a user
    @PostMapping("/login-user/{username}")
    public List<String> userLogin(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        String password = userDto.getPassword();
        return userService.logUser(username, password);
    }
}



