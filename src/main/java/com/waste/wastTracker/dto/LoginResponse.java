package com.waste.wastTracker.dto;

// /////////////generate a token for every login user

public class LoginResponse {  // returns tokens and user info


    private String token; // like a room card in a hotel
    private UserDTO user;

    public LoginResponse(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
}
