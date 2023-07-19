package com.specscapstone.bookReviewApp.controllers;

import com.specscapstone.bookReviewApp.entities.User;
import com.specscapstone.bookReviewApp.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginTest() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Perform login logic using the provided username and password
        // ...
        // Assuming the login is successful, redirect to the home page with the user ID
        User user = userService.getUserByUsername(username);
        if (user != null) {
            long userId = user.getId();
            return "redirect:/home/" + userId;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/home/{username}")
    public ModelAndView home(@PathVariable("username") String username) {
        ModelAndView modelAndView = new ModelAndView("home");

        // Retrieve the user details using the username
        User user = userService.getUserByUsername(username);

        // Pass the user details to the view
        modelAndView.addObject("user", user);
        modelAndView.addObject("loggedInUsername", username);
        modelAndView.addObject("loggedInUserId", user.getId());

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView reviewTest() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
}




