package com.demosql.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demosql.model.entities.User;
import com.demosql.model.request.UserLogin;
import com.demosql.model.request.UserSignUp;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserResponse;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.SignUpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {
    private SignUpView view;
    private ApiService apiService;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void handleSignUp(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showEmptyFieldsError();
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showSignUpFailed();
            return;
        }

        UserSignUp userSignUp = new UserSignUp(email, password, email);
        apiService.register(userSignUp).enqueue(new Callback<ApiResponse<UserSignUpResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<UserSignUpResponse>> call, @NonNull Response<ApiResponse<UserSignUpResponse>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<UserSignUpResponse> apiResponse = response.body();
                    if (apiResponse != null) {
                        view.showSignUpSuccess();
                        view.navigateToLogin();
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get data failed");
                    }
                } else {
                    view.showSignUpFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<UserSignUpResponse>> call, @NonNull Throwable t) {
                Log.e("SignUpPresenter", "Error: " + t.getMessage());
                view.showSignUpFailed();
            }
        });
    }
}
