package com.demosql.presenter;

import com.demosql.model.response.UserDetailResponse;
import com.demosql.view.MainView;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    /*public void loadUserProfile(UserDetailResponse profileResponse) {
        if (profileResponse != null) {
            view.showProfile(profileResponse);
        }
    }*/
}
