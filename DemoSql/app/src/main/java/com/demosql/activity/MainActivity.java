package com.demosql.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.demosql.R;
import com.demosql.databinding.ActivityMainBinding;
import com.demosql.fragment.CartFragment;
import com.demosql.fragment.ProductFragment;
import com.demosql.fragment.ProfileFragment;
import com.demosql.presenter.MainPresenter;
import com.demosql.view.MainView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainView {
    private ActivityMainBinding binding;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this);
        loadFragment(new ProductFragment());
        setActiveButton(binding.productsBtn);
        setupBottomNavigation();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void setupBottomNavigation() {
        binding.productsBtn.setOnClickListener(v -> {
            loadFragment(new ProductFragment());
            setActiveButton(binding.productsBtn);
        });

        binding.cartBtn.setOnClickListener(v -> {
            loadFragment(new CartFragment());
            setActiveButton(binding.cartBtn);
        });

        binding.profileBtn.setOnClickListener(v -> {
            loadFragment(new ProfileFragment());
            setActiveButton(binding.profileBtn);
        });
    }

    private void setActiveButton(android.widget.LinearLayout button) {
        // Đặt background cho tất cả các nút về trạng thái mặc định
        if(Objects.equals(button, binding.productsBtn)) {
            binding.productsBtn.setBackgroundResource(R.drawable.border_button);
            binding.cartBtn.setBackground(null);
            binding.profileBtn.setBackground(null);
        }
        if(Objects.equals(button, binding.cartBtn)) {
            binding.cartBtn.setBackgroundResource(R.drawable.border_button);
            binding.productsBtn.setBackground(null);
            binding.profileBtn.setBackground(null);
        }
        if(Objects.equals(button, binding.profileBtn)) {
            binding.profileBtn.setBackgroundResource(R.drawable.border_button);
            binding.productsBtn.setBackground(null);
            binding.cartBtn.setBackground(null);
        }
    }
}