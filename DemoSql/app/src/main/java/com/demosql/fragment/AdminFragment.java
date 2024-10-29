package com.demosql.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.demosql.R;
import com.demosql.databinding.HomeLayoutBinding;
import com.demosql.model.entities.User;

public class AdminFragment extends Fragment {
    private HomeLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(inflater, container, false);

        // Set the username role
        binding.userName.setText(User.getRole());

        // Set up button listeners
        binding.ordersBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new OrderManagementFragment());
            fragmentTransaction.addToBackStack(null); // Optional: enables back navigation
            fragmentTransaction.commit();
        });

        binding.productsBtn.setOnClickListener(v -> {
            loadFragment(new ProductFragment());
            setActiveButton(binding.productsBtn);
        });

        binding.usersBtn.setOnClickListener(v -> {
//            loadFragment(new UsersFragment()); // Example fragment for user management
            setActiveButton(binding.usersBtn);
        });

        return binding.getRoot();
    }

    private void loadFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void setActiveButton(View button) {
        // Implement this method to style the active button, if needed
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks
    }
}
