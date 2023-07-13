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
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private Integer zipCode;
    private Date dateOfBirth;
    private Set<BookshelfDto> bookshelfDTOSet = new HashSet<>();

    public UserDto (User user) {
        if (user.getUserId() != null) {
            this.userId = user.getUserId();
        }
        if (user.getFirstName() != null) {
            this.firstName = user.getFirstName();
        }
        if (user.getLastName() != null) {
            this.lastName = user.getLastName();
        }
        if (user.getEmail() != null) {
            this.email = user.getEmail();
        }
        if (user.getUsername() != null) {
            this.username = user.getUsername();
        }
        if (user.getPassword() !=null){
            this.password = user.getPassword();
        }
        if (user.getPhoneNumber() != null) {
            this.phoneNumber = user.getPhoneNumber();
        }
        if (user.getAddress() != null) {
            this.address = user.getAddress();
        }
        if (user.getCity() != null) {
            this.city = user.getCity();
        }
        if (user.getState() != null) {
            this.state = user.getState();
        }
        if (user.getZipCode() != null) {
            this.zipCode = user.getZipCode();
        }
        if (user.getDateOfBirth() != null) {
            this.dateOfBirth = user.getDateOfBirth();
        }
    }
}
