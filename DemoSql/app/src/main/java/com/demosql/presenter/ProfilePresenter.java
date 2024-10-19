package com.demosql.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ProfileView view;
    private ApiService apiService;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void loadProfile() {
        apiService.getCurrentUser("Bearer " + User.getToken()).enqueue(new Callback<ApiResponse<UserDetailResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<UserDetailResponse>> call, @NonNull Response<ApiResponse<UserDetailResponse>> response) {
                if (response.isSuccessful()) {
                    UserDetailResponse profileResponse = response.body().getData();
                    if (profileResponse != null) {
                        view.showUserProfile(profileResponse);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<UserDetailResponse>> call, @NonNull Throwable t) {
                Log.e("ProfileFragment", "Error: " + t.getMessage());
            }
        });
    }
}