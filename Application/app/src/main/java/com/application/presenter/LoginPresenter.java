package com.application.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.application.model.DatabaseManager;
import com.application.view.LoginView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPresenter {
    private final LoginView view;
    private final DatabaseManager databaseManager;
    private final ExecutorService executorService;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.databaseManager = new DatabaseManager();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void handleLogin(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsError();
            return;
        } else {
            Log.d("LoginPresenter", "Attempting login with email: " + email);
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
            });
        }
    }
}
