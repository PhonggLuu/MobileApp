package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demosql.databinding.SignupLayoutBinding;
import com.demosql.presenter.SignUpPresenter;
import com.demosql.view.SignUpView;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SignupLayoutBinding binding;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = SignupLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SignUpPresenter(this);

        binding.signup.setOnClickListener(view -> {
            String username = binding.logUsername.getText().toString();
            String password = binding.logPassword.getText().toString();
            String confirmPassword = binding.logRepassword.getText().toString();
            presenter.handleSignUp(username, password, confirmPassword);
        });

        binding.buttonRelogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void showEmptyFieldsError() {
        if (binding.logUsername.getText().toString().isEmpty()) {
            binding.logUsername.setError("Please enter a username");
        }
        if (binding.logPassword.getText().toString().isEmpty()) {
            binding.logPassword.setError("Please enter a password");
        }
        if (binding.logRepassword.getText().toString().isEmpty()) {
            binding.logRepassword.setError("Please confirm your password");
        }
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignUpFailed() {
        Toast.makeText(this, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserExistError() {
        Toast.makeText(this, "User already exists. Please choose another username.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
