package com.intellibet.dto;


import lombok.Data;

@Data
public class UserForm {

    private String firstName;
    private String lastName;
    private String email;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String gender;

    private String address;
    private String postCode;
    private String city;
    private String mobileNumber;
    private String password;
    private String pageSection = "section-1";


}
