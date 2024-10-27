package com.demosql.model.entities;

public class User {
    private static String token;
    private static String imgUrl;
    private static int userId;
    private static String role;
    public static String getToken() {
        return token;
    }
    public static void setToken(String newToken){
        token = newToken;
    }
    public static String getImgUrl() {
        return imgUrl;
    }
    public static void setImgUrl(String newImgUrl) {
        imgUrl = newImgUrl;
    }
    public static int getUserId() {
        return userId;
    }
    public static void setUserId(int userId) {
        User.userId = userId;
    }
    public static String getRole() {
        return role;
    }
    public static void setRole(String role) {
        User.role = role;
    }
}
