package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.User;
import com.demosql.model.request.ProductSearchingRequest;
import com.demosql.model.request.SearchProduct;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PageInfo;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.model.entities.Shirt;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProductManagementView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManagementPresenter {
    private ProductManagementView view;
    private ApiService apiService;
    private Context context;

    public ProductManagementPresenter(Context context, ProductManagementView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
        this.context = context;
    }

    public void loadProducts(ProductSearchingRequest request) {
        apiService.pagingProduct("Bearer " + User.getToken(), request).enqueue(new Callback<ApiResponse<PagingSearchResponse<Shirt>>>() {
            @Override
            public void onResponse(Call<ApiResponse<PagingSearchResponse<Shirt>>> call, Response<ApiResponse<PagingSearchResponse<Shirt>>> response) {
                if (response.isSuccessful()) {
                    PagingSearchResponse pagingProductResponse  = response.body().getData();
                    if (pagingProductResponse != null) {
                        List<Shirt> ProductList = pagingProductResponse.getPageData();
                        PageInfo pageInfo = pagingProductResponse.getPageInfo();
                        view.assignPageInfo(pageInfo.getTotalPage());
                        view.showProducts(ProductList);
                    } else {
                        Toast.makeText(context, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<PagingSearchResponse<Shirt>>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Error: " + throwable.getMessage());
            }
        });
    }
}
