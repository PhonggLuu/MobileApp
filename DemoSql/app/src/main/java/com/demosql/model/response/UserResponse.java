package com.demosql.model.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String newToken) {
        token = newToken;
    }
}


