package com.demosql.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demosql.R;
import com.demosql.databinding.WelcomeLayoutBinding;

public class WelcomeActivity extends AppCompatActivity {
    private WelcomeLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
    }
}
