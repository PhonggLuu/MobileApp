package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.demosql.databinding.ForgotPasswordLayoutBinding;
import com.demosql.presenter.ForgotPasswordPresenter;
import com.demosql.view.ForgotPasswordView;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordView {
    private ForgotPasswordLayoutBinding binding;
    private ForgotPasswordPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ForgotPasswordLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goBackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btnSendRequest.setOnClickListener(v -> {
            String email = binding.email.getText().toString().trim();
            presenter.handleForgotPassword(email);
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.forgotPasswordLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void sendNewPassword(String email) {

    }
}
