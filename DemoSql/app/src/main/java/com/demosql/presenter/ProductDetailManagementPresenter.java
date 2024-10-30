package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
import com.demosql.model.request.UpdateProductRequest;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.ProductSearching;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProductDetailManagementView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailManagementPresenter {
    private ProductDetailManagementView view;
    private Context context;
    private ApiService apiService;

    public ProductDetailManagementPresenter(ProductDetailManagementView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public ProductDetailManagementPresenter(Context context, ProductDetailManagementView view) {
        this.context = context;
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }
    public void getProductDetails(int productId) {
        apiService.getShirtById("Bearer " + User.getToken(), productId).enqueue(new Callback<ApiResponse<ProductSearching>>() {
            @Override
            public void onResponse(Call<ApiResponse<ProductSearching>> call, Response<ApiResponse<ProductSearching>> response) {
                if (response.isSuccessful()) {
                    ProductSearching product  = response.body().getData();
                    if (product != null) {
                        view.showProductDetailed(product);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get product data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ProductSearching>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get product failed");
            }
        });
    }
    public void updateProduct(int productId, UpdateProductRequest request) {
        apiService.updateShirt("Bearer " + User.getToken(), productId, request).enqueue(new Callback<ApiResponse<Shirt>>() {
            @Override
            public void onResponse(Call<ApiResponse<Shirt>> call, Response<ApiResponse<Shirt>> response) {
                if (response.isSuccessful()) {
                    Shirt product  = response.body().getData();
                    if (product != null) {
                        Toast.makeText(context, "Update product successfully", Toast.LENGTH_SHORT).show();
                        getProductDetails(productId);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get product data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Shirt>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get product failed");
            }
        });
    }
}
