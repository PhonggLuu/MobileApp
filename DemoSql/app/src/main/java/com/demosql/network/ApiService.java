package com.demosql.network;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.SearchProduct;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.model.request.UserLogin;
import com.demosql.model.request.UserSignUp;
import com.demosql.model.response.AddToCartResponse;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.CheckoutResponse;
import com.demosql.model.response.PagingShirt;
import com.demosql.model.response.ProductSearching;
import com.demosql.model.response.ProductSearchingPaging;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserResponse;
import com.demosql.model.response.UserSignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @POST("order/updatecart")
    Call<ApiResponse<Cart>> updateQuantity(@Header("Authorization") String token, @Body UpdateCartRequest request);

    @POST("shirt/getallbyname")
    Call<ApiResponse<PagingShirt>> searchProduct(@Header("Authorization") String token, String query);

    @POST("order/deteleitemincart")
    Call<ApiResponse<Cart>> removeItemFromCart(@Header("Authorization") String token, @Body RemoveItemInCartRequest request);

    @DELETE("order/{id}")
    Call<ApiResponse<CheckoutResponse>> checkout(@Header("Authorization") String token, @Path("id") String id, @Query("status") int status);

    @GET("shirt/{id}")
    Call<ApiResponse<ProductSearching>> getShirtById(@Header("Authorization") String token, @Path("id") int id);
}
