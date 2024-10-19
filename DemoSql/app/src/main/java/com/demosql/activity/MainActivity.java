package com.demosql.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.demosql.R;
import com.demosql.databinding.ActivityMainBinding;
import com.demosql.fragment.CartFragment;
import com.demosql.fragment.ProfileFragment;
import com.demosql.model.response.UserDetailResponse;
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

        //UserDetailResponse profileResponse = (UserDetailResponse) getIntent().getSerializableExtra("USER_DETAIL");

        /*presenter = new MainPresenter(this);
        presenter.loadUserProfile(profileResponse);*/
        setupBottomNavigation();
    }

    /*@Override
    public void showProfile(UserDetailResponse profileResponse) {
        ProfileFragment profileFragment = new ProfileFragment(profileResponse);
        loadFragment(profileFragment);
    }*/

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void setupBottomNavigation() {
        /*findViewById(R.id.productsBtn).setOnClickListener(v -> {
            loadFragment(new ProductsFragment()); // Chuyển đến ProductsFragment
        });*/

        findViewById(R.id.cartBtn).setOnClickListener(v -> {
            loadFragment(new CartFragment()); // Chuyển đến CartFragment
        });

        binding.profileBtn.setOnClickListener(v -> {
            UserDetailResponse profileResponse = (UserDetailResponse) getIntent().getSerializableExtra("USER_DETAIL");
            loadFragment(new ProfileFragment(profileResponse));
        });
    }
}