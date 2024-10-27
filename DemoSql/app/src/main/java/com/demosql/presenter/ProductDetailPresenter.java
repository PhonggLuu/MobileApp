package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.ProductSearching;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProductDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailPresenter {
    private ProductDetailView view;
    private Context context;
    private ApiService apiService;

    public ProductDetailPresenter(ProductDetailView view) {
        this.view = view;
    }

    public ProductDetailPresenter(Context context, ProductDetailView view) {
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
                        Toast.makeText(context, "Chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
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
}
