package com.specscapstone.bookReviewApp.dtos;


import com.specscapstone.bookReviewApp.entities.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;


    public UserDto(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
