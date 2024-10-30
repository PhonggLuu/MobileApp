package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demosql.R;
import com.demosql.databinding.SignupLayoutBinding;
import com.demosql.presenter.SignUpPresenter;
import com.demosql.view.SignUpView;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SignupLayoutBinding binding;
    private SignUpPresenter presenter;
    private boolean isPasswordVisible;
    private boolean isRepasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = SignupLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SignUpPresenter(this);

        binding.signup.setOnClickListener(view -> {
            String email = binding.logEmail.getText().toString();
            String password = binding.logPassword.getText().toString();
            String confirmPassword = binding.logRepassword.getText().toString();
            String fullName = binding.logFullname.getText().toString();
            String phoneNumber = binding.logPhoneNumber.getText().toString();
            presenter.handleSignUp(email, password, confirmPassword, fullName, phoneNumber);
        });

        binding.ivEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    binding.logPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.ivEye1.setImageResource(R.drawable.baseline_remove_red_eye_24);
                } else {
                    binding.logPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                binding.logPassword.setSelection(binding.logPassword.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });

        binding.ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRepasswordVisible) {
                    binding.logRepassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.ivEye1.setImageResource(R.drawable.baseline_remove_red_eye_24);
                } else {
                    binding.logRepassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                binding.logRepassword.setSelection(binding.logRepassword.length());
                isRepasswordVisible = !isRepasswordVisible;
            }
        });

        binding.buttonRelogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void showEmptyFieldsError() {
        if (binding.logEmail.getText().toString().isEmpty()) {
            binding.logEmail.setError("Please enter a email");
        }
        if (binding.logPassword.getText().toString().isEmpty()) {
            binding.logPassword.setError("Please enter a password");
        }
        if (binding.logRepassword.getText().toString().isEmpty()) {
            binding.logRepassword.setError("Please confirm your password");
        }
        if (binding.logFullname.getText().toString().isEmpty()) {
            binding.logFullname.setError("Please enter your name");
        }
        if (binding.logPhoneNumber.getText().toString().isEmpty()) {
            binding.logPhoneNumber.setError("Please enter your phone number");
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
    public void showEmailFailed() {
        Toast.makeText(this, "Email is not valid. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
