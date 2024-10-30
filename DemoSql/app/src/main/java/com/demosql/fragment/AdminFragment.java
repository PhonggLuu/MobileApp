package com.demosql.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.demosql.R;
import com.demosql.databinding.HomeLayoutBinding;
import com.demosql.model.entities.User;

public class AdminFragment extends Fragment {
    private HomeLayoutBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(getLayoutInflater());
        binding.userName.setText(User.getRole());
        binding.ordersBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new OrderManagementFragment());
            fragmentTransaction.addToBackStack(null); // Optional: if you want to allow users to navigate back
            fragmentTransaction.commit();
        });
        binding.productsBtn.setOnClickListener(v -> {

        });
        binding.usersBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new UserManagementFragment());
            fragmentTransaction.addToBackStack(null); // Optional: if you want to allow users to navigate back
            fragmentTransaction.commit();
        });
        return binding.getRoot();
    }
}
