package com.demosql.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demosql.databinding.ProfileLayoutBinding;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.presenter.ProfilePresenter;
import com.demosql.view.ProfileView;

public class ProfileFragment extends Fragment implements ProfileView {
    private ProfilePresenter presenter;
    private UserDetailResponse userDetail;
    private ProfileLayoutBinding binding;

    public ProfileFragment(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileLayoutBinding.inflate(inflater, container, false); // Initialize binding first
        View view = binding.getRoot();

        presenter = new ProfilePresenter(this);
        presenter.loadUserDetails(userDetail); // Load user details

        return view;
    }

    @Override
    public void showUserProfile(UserDetailResponse userDetail) {
        binding.userName.setText(userDetail.getUserName());
        binding.userEmail.setText(userDetail.getEmail());
        binding.userPhone.setText(userDetail.getPhoneNumber());
        binding.userAddress.setText(userDetail.getAddress());
    }
}
