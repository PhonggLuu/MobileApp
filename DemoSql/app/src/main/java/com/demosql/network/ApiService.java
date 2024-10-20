package com.demosql.network;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UserLogin;
import com.demosql.model.request.UserSignUp;
import com.demosql.model.response.AddToCartResponse;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingShirt;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserResponse;
import com.demosql.model.response.UserSignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("user/login")
    Call<ApiResponse<UserResponse>> login(@Body UserLogin userLogin);

    @GET("user/current-user")
    Call<ApiResponse<UserDetailResponse>> getCurrentUser(@Header("Authorization") String token);

    @POST("shirt/getall")
    Call<ApiResponse<PagingShirt>> getShirtList(@Header("Authorization") String token);

    @GET("order/cart")
    Call<ApiResponse<Cart>> getMyCart(@Header("Authorization") String token);

    @POST("user")
    Call<ApiResponse<UserSignUpResponse>> register(@Body UserSignUp userSignUp);

    @POST("order/addtocart")
    Call<ApiResponse<Cart>> addToCart(@Header("Authorization") String token, @Body ShirtRequest shirt);
}
