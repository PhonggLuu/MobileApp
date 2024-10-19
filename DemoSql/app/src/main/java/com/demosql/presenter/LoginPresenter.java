package com.demosql.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demosql.model.entities.User;
import com.demosql.model.request.UserLogin;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView view;
    private final ApiService apiService;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void handleLogin(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsError();
        } else {
            UserLogin userLogin = new UserLogin("luuhiep16092002@gmail.com", "Luuhiep113@");
            apiService.login(userLogin).enqueue(new Callback<ApiResponse<UserResponse>>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse<UserResponse>> call, @NonNull Response<ApiResponse<UserResponse>> response) {
                    if (response.isSuccessful()) {
                        ApiResponse<UserResponse> apiResponse = response.body();
                        UserResponse userResponse = apiResponse.getData();
                        if (userResponse != null) {
                            String token = userResponse.getToken();
                            /*User.setToken(token);
                            Log.d("Token", token);*/
                            view.showLoginSuccess();
                            // Gọi API để lấy hồ sơ người dùng
                            apiService.getCurrentUser("Bearer " + token).enqueue(new Callback<ApiResponse<UserDetailResponse>>() {
                                @Override
                                public void onResponse(@NonNull Call<ApiResponse<UserDetailResponse>> call, @NonNull Response<ApiResponse<UserDetailResponse>> response) {
                                    if (response.isSuccessful()) {
                                        UserDetailResponse profileResponse = response.body().getData();
                                        if (profileResponse != null) {
                                            view.navigateToMainAndProfile(profileResponse);
                                        } else {
                                            Log.e(this.getClass().getName(), "Error: Get profile data failed");
                                        }
                                    } else {
                                        Log.e(this.getClass().getName(), "Error: " + response.message());
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<ApiResponse<UserDetailResponse>> call, @NonNull Throwable t) {
                                    Log.e("LoginPresenter", "Error: " + t.getMessage());
                                    view.showLoginError("Error: " + t.getMessage());
                                }
                            });
                        } else {
                            Log.e(this.getClass().getName(), "Error: Get data failed");
                        }
                    } else {
                        view.showLoginFailed();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse<UserResponse>> call, @NonNull Throwable t) {
                    Log.e("LoginPresenter", "Error: " + t.getMessage());
                    view.showLoginError("Error: " + t.getMessage());
                }
            });
        }
    }
}
