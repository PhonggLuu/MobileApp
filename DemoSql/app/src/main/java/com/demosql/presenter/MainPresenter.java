package com.demosql.presenter;

import android.util.Log;

import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingShirt;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }
}
