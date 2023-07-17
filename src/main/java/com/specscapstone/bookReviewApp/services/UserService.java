package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.UserDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(String username, String password);
}
