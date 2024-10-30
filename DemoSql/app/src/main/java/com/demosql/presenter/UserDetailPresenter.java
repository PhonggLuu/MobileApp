package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.UserDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailPresenter {
    private UserDetailView view;
    private Context context;
    private ApiService apiService;

    public UserDetailPresenter(UserDetailView view) {
        this.view = view;
    }

    public UserDetailPresenter(Context context, UserDetailView view) {
        this.context = context;
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public void getUserDetails(int UserId) {
        apiService.getUserById("Bearer " + User.getToken(), UserId).enqueue(new Callback<ApiResponse<UserSignUpResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserSignUpResponse>> call, Response<ApiResponse<UserSignUpResponse>> response) {
                if (response.isSuccessful()) {
                    UserSignUpResponse User  = response.body().getData();
                    if (User != null) {
                        Toast.makeText(context, "Chi tiết người dùng", Toast.LENGTH_SHORT).show();
                        view.showUserDetailed(User);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get User data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserSignUpResponse>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get User failed");
            }
        });
    }

    public void lockAccount(int UserId) {
        apiService.blockUser("Bearer " + User.getToken(), UserId).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Chặn người dùng thành công", Toast.LENGTH_SHORT).show();
                    getUserDetails(UserId);
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Block user failed");
            }
        });
    }
}
