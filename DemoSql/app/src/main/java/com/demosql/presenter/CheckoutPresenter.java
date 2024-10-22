package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.CheckoutResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.CheckoutView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutPresenter {
    private CheckoutView view;
    private ApiService apiService;

    public CheckoutPresenter(CheckoutView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public void checkout(String orderId, int status) {
        apiService.checkout("Bearer " + User.getToken(), orderId, status).enqueue(new Callback<ApiResponse<CheckoutResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<CheckoutResponse>> call, Response<ApiResponse<CheckoutResponse>> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    CheckoutResponse order = (CheckoutResponse) apiResponse.getData();
                    if (apiResponse != null) {
                        //Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(this.getClass().getName(), "Xóa sản phẩm khỏi giỏ hàng thất bại");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CheckoutResponse>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get produc failed");
            }
        });
    }
}
