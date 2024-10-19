package com.demosql.presenter;

import com.demosql.view.SignUpView;

public class SignUpPresenter {
    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    public void handleSignUp(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showEmptyFieldsError();
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showSignUpFailed();
            return;
        }

        if (userExists(username)) {
            view.showUserExistError();
        } else {
            view.showSignUpSuccess();
        }
    }

    private boolean userExists(String username) {
        return username.equals("existingUser");
    }
}
