package com.demosql.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.demosql.activity.MainActivity;
import com.demosql.databinding.UserDetailManagementLayoutBinding;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.presenter.UserDetailPresenter;
import com.demosql.view.UserDetailView;

public class UserDetailFragment extends Fragment implements UserDetailView {
    private UserDetailPresenter presenter;
    private UserDetailManagementLayoutBinding binding;

    public UserDetailFragment() {

    }

    public static UserDetailFragment newInstance(int UserId) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putInt("UserId", UserId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new UserDetailPresenter(getContext(), this);
        binding = UserDetailManagementLayoutBinding.inflate(getLayoutInflater());
        int UserId = getArguments().getInt("UserId");
        presenter.getUserDetails(UserId);
        binding.goBackBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });
        return binding.getRoot();
    }

    @Override
    public void showUserDetailed(UserSignUpResponse User) {
        Glide.with(this)
                .load(User.getImgUrl())
                .into(binding.userImage);
        binding.userName.setText(User.getUserName());
        binding.email.setText(User.getEmail());
        binding.dateOfBirth.setText(User.getDob());
        binding.address.setText(User.getAddress());
        binding.phoneNumber.setText(User.getPhoneNumber());
        binding.roleName.setText(String.valueOf(User.getRoleName()));
        if(User.isDelete()) {
            binding.isDelete.setText("Đã bị khóa");
            binding.lockAccountBtn.setVisibility(View.GONE);
        } else {
            binding.isDelete.setText("Đang hoạt động");
        }
        if(User.isVerify()) {
            binding.isVerify.setText("Đã xác minh");
        } else {
            binding.isVerify.setText("Chưa xác minh");
        }
        binding.lockAccountBtn.setOnClickListener(v -> {
            presenter.lockAccount(User.getId());
        });
    }
}
