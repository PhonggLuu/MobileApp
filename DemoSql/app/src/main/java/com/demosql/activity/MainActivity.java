package com.demosql.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.demosql.R;
import com.demosql.databinding.ActivityMainBinding;
import com.demosql.fragment.CartFragment;
import com.demosql.fragment.ProductFragment;
import com.demosql.fragment.ProfileFragment;
import com.demosql.presenter.MainPresenter;
import com.demosql.view.MainView;

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
        });

        binding.cartBtn.setOnClickListener(v -> {
            loadFragment(new CartFragment()); // Chuyển đến CartFragment
        });

        binding.profileBtn.setOnClickListener(v -> {
            loadFragment(new ProfileFragment());
        });
    }
}