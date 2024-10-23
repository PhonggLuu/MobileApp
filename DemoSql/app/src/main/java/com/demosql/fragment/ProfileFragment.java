package com.demosql.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.demosql.databinding.ProfileLayoutBinding;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.presenter.ProfilePresenter;
import com.demosql.view.ProfileView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileFragment extends Fragment implements ProfileView {
    private ProfilePresenter presenter;
    private ProfileLayoutBinding binding;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileLayoutBinding.inflate(inflater, container, false); // Initialize binding first
        View view = binding.getRoot();

        presenter = new ProfilePresenter(this);
        presenter.loadProfile(); // Load user details

        binding.btnLogOut.setOnClickListener(v -> {
            //Intent intent = new Intent(getActivity(), EditProfileActivity.class);

        });
        return view;
    }

    @Override
    public void showUserProfile(UserDetailResponse userDetail) {

        Glide.with(this)
                .load(userDetail.getImgUrl())
                .into(binding.avatar);
        binding.userName.setText(userDetail.getUserName());
        binding.userEmail.setText(userDetail.getEmail());
        binding.phone.setText(userDetail.getPhoneNumber());
        binding.address.setText(userDetail.getAddress());
        String dobString = userDetail.getDob();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(dobString);
            String formattedDate = outputFormat.format(date);
            binding.dateOfBirth.setText(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
