package com.demosql.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.demosql.R;
import com.demosql.databinding.AdminLayoutBinding;
import com.demosql.fragment.AdminFragment;
import com.demosql.fragment.OrderManagementFragment;
import com.demosql.fragment.ProductFragment;
import com.demosql.fragment.ProfileFragment;

public class MainAdminActivity extends AppCompatActivity {
    private AdminLayoutBinding binding;
    private View activeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //presenter = new MainPresenter(this);
        loadFragment(new AdminFragment());
        setActiveButton(binding.homeBtn);
        setupBottomNavigation();
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
    private void setActiveButton(View activeBtn) {
        if (activeButton != null) {
            //activeButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            activeButton.setBackground(null);
        }
        activeBtn.setBackgroundResource(R.drawable.bg_btn_change);
        activeButton = activeBtn;
    }

    private void setupBottomNavigation() {
        binding.homeBtn.setOnClickListener(view -> {
            loadFragment(new AdminFragment());
            setActiveButton(binding.homeBtn);
        });
        binding.userBtn.setOnClickListener(view -> {
            loadFragment(new ProfileFragment());
            setActiveButton(binding.userBtn);
        });
        binding.productBtn.setOnClickListener(view -> {
            loadFragment(new ProductFragment());
            setActiveButton(binding.productBtn);
        });
        binding.orderBtn.setOnClickListener(view -> {
            loadFragment(new OrderManagementFragment());
            setActiveButton(binding.orderBtn);
        });
    }
}
