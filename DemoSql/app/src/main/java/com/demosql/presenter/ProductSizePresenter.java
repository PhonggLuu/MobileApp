package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.ShirtSize;
import com.demosql.model.entities.User;
import com.demosql.model.request.ShirtSizeRequest;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.ProductDetailManagementView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSizePresenter {
    private Context context;
    private ApiService apiService;
    private ProductDetailManagementView view;
    private ProductDetailManagementPresenter presenter;

    public ProductSizePresenter(Context context, ProductDetailManagementView view) {
        this.context = context;
        this.view = view;
        this.presenter = new ProductDetailManagementPresenter(view);
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public void updateSize(int id, ShirtSizeRequest shirtSize) {
        apiService.updateShirtSize("Bearer " + User.getToken(), id, shirtSize).enqueue(new Callback<ApiResponse<ShirtSize>>() {
            @Override
            public void onResponse(Call<ApiResponse<ShirtSize>> call, Response<ApiResponse<ShirtSize>> response) {
                if (response.isSuccessful()) {
                    ShirtSize size  = response.body().getData();
                    if (size != null) {
                        Toast.makeText(context, "Update size successfully", Toast.LENGTH_SHORT).show();
                        Log.e("ProductPresenter", view +"");
                        presenter.getProductDetails(shirtSize.getShirtId());
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ShirtSize>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Update size failed");
            }
        });
    }
}
