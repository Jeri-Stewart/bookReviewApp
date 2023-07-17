package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.dtos.UserDto;
import com.specscapstone.bookReviewApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // To register a new user
    @PostMapping("/register-user")
    public List<String> userRegister(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        List<String> response = userService.addUser(userDto);
        System.out.println("UserController - addUser response: " + response);
        return response;
    }

    // To log in a user
    @PostMapping("/login-user")
    public List<String> userLogin(@RequestParam String username, @RequestParam String password) {
        return userService.userLogin(username, password);
    }
}
