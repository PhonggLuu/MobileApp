package com.demosql.model.response;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private UserSignUpResponse userResponse;

    public String getToken() {
        return token;
    }

    public void setToken(String newToken) {
        token = newToken;
    }

    public UserSignUpResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserSignUpResponse userResponse) {
        this.userResponse = userResponse;
    }
}


