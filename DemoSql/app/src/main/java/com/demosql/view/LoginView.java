package com.demosql.view;

import com.demosql.model.response.UserDetailResponse;

public interface LoginView {
    void showEmptyFieldsError();
    void showLoginSuccess();
    void showLoginFailed();
    void showLoginError(String s);
    void navigateToMainAndProfile(UserDetailResponse profileResponse);
    void navigateToMain();
}
