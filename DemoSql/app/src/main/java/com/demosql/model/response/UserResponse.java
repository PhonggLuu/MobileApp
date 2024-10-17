package com.demosql.model.response;

import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("userID")
    private UUID userID;

    @SerializedName("token")
    private String token;

    @SerializedName("expiration")
    private String expiration;

    @SerializedName("role")
    private String role;

    @SerializedName("isVerification")
    private boolean isVerification;

    // Getters and Setters
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isVerification() {
        return isVerification;
    }

    public void setVerification(boolean isVerification) {
        this.isVerification = isVerification;
    }
}


