package com.application.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.application.databinding.SigninLayoutBinding;
import com.application.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private SigninLayoutBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SigninLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this);
        binding.login.setOnClickListener(view ->
        {
            String email = binding.logUsername.getText().toString().trim();
            String password = binding.logPassword.getText().toString().trim();
            try {
                presenter.handleLogin(email, password);
            } catch (Exception e) {
                Log.e("LoginActivity", "Error during login", e);
                Toast.makeText(this, "Có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.signup.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.dangnhap, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void navigateToWelcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();  // Optional: finish the LoginActivity if you don’t want to return to it
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Email và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String s) {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
    }
}
