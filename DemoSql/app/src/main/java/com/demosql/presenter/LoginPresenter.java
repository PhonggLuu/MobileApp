package com.demosql.presenter;

import android.util.Log;

//import com.demosql.model.DatabaseManager;
import androidx.annotation.NonNull;

import com.demosql.model.dto.UserLogin;
import com.demosql.model.response.UserResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView view;
    //private final DatabaseManager databaseManager;
    //private final ExecutorService executorService;
    private final ApiService apiService;

    public LoginPresenter(LoginView view) {
        this.view = view;
        //this.databaseManager = new DatabaseManager();
        //this.executorService = Executors.newSingleThreadExecutor();
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void handleLogin(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsError();
        } else {
            UserLogin userLogin = new UserLogin("luuphong016@gmail.com", "Phong9702@");
            apiService.login(userLogin).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        UserResponse userResponse = response.body();
                        if (userResponse != null) {
                            // Xử lý phản hồi thành công
                            view.showLoginSuccess();
                        } else {
                            view.showLoginFailed();
                        }
                    } else {
                        view.showLoginFailed();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                    view.showLoginError("Error: " + t.getMessage());
                }
            });
            /*Log.d("LoginPresenter", "Attempting login with email: " + email);
            executorService.execute(() -> {
                try {
                    boolean success = databaseManager.login(email, password);
                    //Sử dụng Handler để chuyển tiếp về luồng chính
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if(success) {
                            view.showLoginSuccess();
                            view.navigateToWelcome();
                        } else {
                            view.showLoginFailed();
                        }
                    });
                } catch (Exception e) {
                    Log.d("LoginPresenter", "Attempting login with email: " + email);
                    new Handler(Looper.getMainLooper()).post(() -> view.showLoginError("Có lỗi xảy ra khi đăng nhập. Vui lòng thử lại."));
                }
            });*/
        }
    }
}
