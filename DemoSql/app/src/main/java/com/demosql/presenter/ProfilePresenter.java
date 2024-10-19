package com.demosql.presenter;

import com.demosql.model.response.UserDetailResponse;
import com.demosql.view.ProfileView;

public class ProfilePresenter {
    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    public void loadUserDetails(UserDetailResponse userDetail) {
        if (userDetail != null) {
            view.showUserProfile(userDetail);
        }
    }
}