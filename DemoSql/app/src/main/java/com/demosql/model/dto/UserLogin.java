package com.demosql.model.dto;

public class UserLogin {
    public String email;
    public String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter và Setter
    public String getEmail() {
        return email;
    }

    public void setSmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
