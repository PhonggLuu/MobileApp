package com.demosql.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.demosql.databinding.ForgotPasswordLayoutBinding;
import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserLoginResponse;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ForgotPasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordPresenter {
    private Context context;
    private ForgotPasswordView view;
    private ForgotPasswordLayoutBinding binding;
    private ApiService apiService;

    public ForgotPasswordPresenter(Context context, ForgotPasswordView view) {
        this.context = context;
        this.view = view;
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void handleForgotPassword(String email) {
    }
}
