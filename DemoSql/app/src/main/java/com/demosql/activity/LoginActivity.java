package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.demosql.R;
import com.demosql.databinding.SigninLayoutBinding;
import com.demosql.presenter.LoginPresenter;
import com.demosql.view.LoginView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private SigninLayoutBinding binding;
    private LoginPresenter presenter;
    private AdView adView;
    private boolean isPasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SigninLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize presenter
        presenter = new LoginPresenter(this);

        // Initialize AdView and load ad
        adView = binding.adView;
        MobileAds.initialize(LoginActivity.this, initializationStatus -> {
            Log.d("AdMob", "AdMob initialized");
            loadAd();
        });

        binding.ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    binding.logPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.ivEye.setImageResource(R.drawable.baseline_remove_red_eye_24); // Hình ảnh 'ẩn'
                } else {
                    binding.logPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                binding.logPassword.setSelection(binding.logPassword.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });


        // Set up login button click listener
        binding.login.setOnClickListener(view -> {
            String email = binding.logUsername.getText().toString().trim();
            String password = binding.logPassword.getText().toString().trim();
            /*if (email.isEmpty() || password.isEmpty()) {
                showEmptyFieldsError();
            } else {*/
                try {
                    presenter.handleLogin(email, password);
                } catch (Exception e) {
                    Log.e("LoginActivity", "Error during login", e);
                    Toast.makeText(this, "Có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            //}
        });

        binding.forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
            finish();
        });

        // Set up signup button click listener
        binding.signup.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        // Handle window insets for padding adjustments
        ViewCompat.setOnApplyWindowInsetsListener(binding.dangnhap, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Load the ad into AdView
    private void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        if (adView != null) {
            adView.loadAd(adRequest);
        } else {
            Log.e("LoginActivity", "AdView is null, cannot load ad.");
        }
    }

    @Override
    public void navigateToMainAdmin() {
        Intent intent = new Intent(this, MainAdminActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Email và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
