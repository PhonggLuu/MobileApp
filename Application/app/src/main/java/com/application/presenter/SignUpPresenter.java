package com.application.presenter;

import com.application.view.SignUpView;

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
            view.showSignUpFailed(); // Could also be a specific error for mismatched passwords
            return;
        }

        // Simulate a sign-up process (replace this with actual registration logic)
        if (userExists(username)) {
            view.showUserExistError();
        } else {
            // Here you would typically call a model to register the user
            view.showSignUpSuccess();
        }
    }

    private boolean userExists(String username) {
        // Dummy check; in a real app, you'd check against a database or API
        return username.equals("existingUser");
    }
}
