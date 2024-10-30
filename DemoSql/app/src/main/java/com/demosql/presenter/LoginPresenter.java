package com.demosql.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demosql.model.entities.User;
import com.demosql.model.request.UserLogin;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserLoginResponse;
import com.demosql.model.response.UserSignUpResponse;
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
        /*if(email.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsError();
        } else {*/
            //UserLogin userLogin = new UserLogin("luuhiep16092002@gmail.com", "Luuhiep113@");
            UserLogin userLogin = new UserLogin("luuphong016@gmail.com", "Phong9702@");
            //UserLogin userLogin = new UserLogin("quangde40@gmail.com", "123456");
            //UserLogin userLogin = new UserLogin("nguyenngoc@gmail.com", "Nguyen123");
            //UserLogin userLogin = new UserLogin("hoangnq", "hoangtoc123");
            //UserLogin userLogin = new UserLogin(email, password);
            apiService.login(userLogin).enqueue(new Callback<ApiResponse<UserLoginResponse>>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse<UserLoginResponse>> call, @NonNull Response<ApiResponse<UserLoginResponse>> response) {
                    if (response.isSuccessful()) {
                        ApiResponse<UserLoginResponse> apiResponse = response.body();
                        UserLoginResponse userLoginResponse = apiResponse.getData();
                        if (userLoginResponse != null) {
                            String token = userLoginResponse.getToken();
                            User.setToken(token);
                            UserSignUpResponse userResponse = userLoginResponse.getUserResponse();
                            User.setUserId(userResponse.getId());
                            User.setImgUrl(userResponse.getImgUrl());
                            User.setRole(userResponse.getRoleName());
                            Log.d("Token", token);
                            view.showLoginSuccess();
                            if(User.getRole().equalsIgnoreCase("admin")) {
                                view.navigateToMainAdmin();
                            } else {
                                view.navigateToMain();
                            }
                        } else {
                            Log.e(this.getClass().getName(), "Error: Get data failed");
                        }
                    } else {
                        view.showLoginFailed();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse<UserLoginResponse>> call, @NonNull Throwable t) {
                    Log.e("LoginPresenter", "Error: " + t.getMessage());
                    view.showLoginError("Error: " + t.getMessage());
                }
            });
        //}
    }
}
