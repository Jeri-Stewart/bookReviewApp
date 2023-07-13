package com.specscapstone.bookReviewApp.entities;

import jakarta.persistence.*;
import java.util.*;
import com.specscapstone.bookReviewApp.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    // table relationships

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Set<Bookshelf> bookshelf;

    public User(UserDto userDto) {
        if (userDto.getFirstName() != null) {
            this.firstName = userDto.getFirstName();
        }
        if (userDto.getLastName() != null) {
            this.lastName = userDto.getLastName();
        }
        if (userDto.getEmail() != null) {
            this.email = userDto.getEmail();
        }
        if (userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if (userDto.getPhoneNumber() != null) {
            this.phoneNumber = userDto.getPhoneNumber();
        }
        if (userDto.getAddress() != null) {
            this.address = userDto.getAddress();
        }
        if (userDto.getCity() != null) {
            this.city = userDto.getCity();
        }
        if (userDto.getState() != null) {
            this.state = userDto.getState();
        }
        if (userDto.getZipCode() != null) {
            this.zipCode = userDto.getZipCode();
        }
        if (userDto.getDateOfBirth() != null) {
            this.dateOfBirth = userDto.getDateOfBirth();
        }
    }
}
