package com.example.demo;

public enum UserType {
    ADMIN("Admin"),
    COMMMON("Common");


    private String userType;

    UserType(String userType){
        this.userType = userType;
    }


    public String getUserType(){
        return userType;
    }


}