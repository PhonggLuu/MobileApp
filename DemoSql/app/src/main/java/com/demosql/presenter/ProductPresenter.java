package com.demosql.presenter;

import android.util.Log;

import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingShirt;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProductView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {
    private ProductView view;
    private ApiService apiService;

    public ProductPresenter(ProductView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public void loadProducts() {
        apiService.getShirtList("Bearer " + User.getToken()).enqueue(new Callback<ApiResponse<PagingShirt>>() {
            @Override
            public void onResponse(Call<ApiResponse<PagingShirt>> call, Response<ApiResponse<PagingShirt>> response) {
                if (response.isSuccessful()) {
                    PagingShirt pagingShirt  = response.body().getData();
                    List<Shirt> shirts = pagingShirt.getShirtList();
                    if (shirts != null) {
                        Log.e("ProductPresenter", view +"");
                        view.showProducts(shirts);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<PagingShirt>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get produc failed");
            }
        });
    }
}
