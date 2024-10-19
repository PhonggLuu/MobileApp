package com.demosql.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.demosql.R;
import com.demosql.model.response.UserDetailResponse;

public class MainFragment extends Fragment {
/*    private UserDetailResponse profileResponse;

    public void setProfileResponse(UserDetailResponse profileResponse) {
        this.profileResponse = profileResponse;
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }
}

