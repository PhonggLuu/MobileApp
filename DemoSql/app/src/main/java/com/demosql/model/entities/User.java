package com.demosql.model.entities;

public class User {
    private static String token;
    public static String getToken() {
        return token;
    }
    public static void setToken(String newToken){
        token = newToken;
    }
}
