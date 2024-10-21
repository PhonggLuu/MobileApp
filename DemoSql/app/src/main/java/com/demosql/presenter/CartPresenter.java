package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.response.AddToCartResponse;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingShirt;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.CartView;
import com.demosql.view.ProductView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {
    private Context context;
    private CartView view;
    private ApiService apiService;

    public CartPresenter(Context context, CartView view) {
        this.context = context;
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public void addToCart(ShirtRequest shirt) {
        apiService.addToCart("Bearer " + User.getToken(), shirt).enqueue(new Callback<ApiResponse<Cart>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cart>> call, Response<ApiResponse<Cart>> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Cart cart = (Cart) apiResponse.getData();
                    List<CartDetails> cartDetails = cart.getOrderDetails();
                    if (apiResponse != null) {
                        Log.e( this.getClass().getName(), "Add to cart success");
                        //view.addToCart(shirt);
                        view.showCart(cartDetails);
                        Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(this.getClass().getName(), "Thêm vào giỏ hàng thất bại");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Cart>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get produc failed");
            }
        });
    }

    public void loadCart() {
        apiService.getMyCart("Bearer " + User.getToken()).enqueue(new Callback<ApiResponse<Cart>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cart>> call, Response<ApiResponse<Cart>> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Cart cart = (Cart) apiResponse.getData();
                    List<CartDetails> cartDetails = cart.getOrderDetails();
                    if (apiResponse != null) {
                        view.showCart(cartDetails);
                    } else {
                        Log.e(this.getClass().getName(), "Thêm vào giỏ hàng thất bại");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Cart>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get produc failed");
            }
        });
    }
}
