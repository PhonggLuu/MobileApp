package com.demosql.view;

public interface LoginView {
    void showEmptyFieldsError();
    void showLoginSuccess();
    void showLoginFailed();
    void showLoginError(String s);
    void navigateToWelcome();
}
