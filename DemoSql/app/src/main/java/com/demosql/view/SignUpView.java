package com.demosql.view;

public interface SignUpView {
    void showEmptyFieldsError();
    void showSignUpSuccess();
    void showSignUpFailed();
    void showEmailFailed();
    void navigateToLogin();
}
