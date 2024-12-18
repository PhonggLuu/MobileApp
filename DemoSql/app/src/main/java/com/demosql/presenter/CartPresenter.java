package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.model.response.ApiResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.CartView;

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
                        //view.addToCart(shirt);
                        view.showCart(cartDetails);
                        Toast.makeText(context, "Cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(this.getClass().getName(), "Cập nhật vào giỏ hàng thất bại");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Cart>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get cart failed");
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
                    if (apiResponse != null) {
                        if(cart == null) {
                            view.showCart(null);
                        }
                        else {
                            view.showCart(cart.getOrderDetails());
                        }
                    } else {
                        Log.e(this.getClass().getName(), "Giỏ hàng trống");
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

    public void updateCart(UpdateCartRequest request) {
        apiService.updateQuantity("Bearer " + User.getToken(), request).enqueue(new Callback<ApiResponse<Cart>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cart>> call, Response<ApiResponse<Cart>> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Cart cart = (Cart) apiResponse.getData();
                    List<CartDetails> cartDetails = cart.getOrderDetails();
                    if (apiResponse != null) {
                        Log.e( this.getClass().getName(), "Update cart success");
                        //view.addToCart(shirt);
                        view.showCart(cartDetails);
                        Toast.makeText(context, "Cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(this.getClass().getName(), "Cập nhật giỏ hàng thất bại");
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

    public void removeItem(RemoveItemInCartRequest request) {
        apiService.removeItemFromCart("Bearer " + User.getToken(), request).enqueue(new Callback<ApiResponse<Cart>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cart>> call, Response<ApiResponse<Cart>> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Cart cart = (Cart) apiResponse.getData();
                    List<CartDetails> cartDetails = cart.getOrderDetails();
                    if (apiResponse != null) {
                        Log.e( this.getClass().getName(), "Remove item success");
                        //view.addToCart(shirt);
                        view.showCart(cartDetails);
                        Toast.makeText(context, "Xóa sản phẩm khỏi giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(this.getClass().getName(), "Xóa sản phẩm khỏi giỏ hàng thất bại");
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
