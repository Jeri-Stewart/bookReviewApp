package com.specscapstone.bookReviewApp.services;

import com.specscapstone.bookReviewApp.dtos.UserDto;
import com.specscapstone.bookReviewApp.entities.User;
import jakarta.transaction.Transactional;
import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    User getUserById(long userId);

    User getUserByUsername(String username);

    List<String> logUser(String username, String password);
}
