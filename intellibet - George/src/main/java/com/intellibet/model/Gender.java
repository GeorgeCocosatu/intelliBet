package com.intellibet.model;

public enum Gender {
    MALE, FEMALE, NONBINARY;

    public static Gender getGenderFromString(String gender){
        if(gender.equals("male")){
            return MALE;
        }
        if(gender.equals("female")){
            return FEMALE;
        }
        if(gender.equals("non-binary")){
            return NONBINARY;
        }
        throw new RuntimeException("The gender is not valid!");
    }
}
