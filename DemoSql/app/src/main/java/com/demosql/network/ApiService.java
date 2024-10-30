package com.demosql.network;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.Order;
import com.demosql.model.entities.OrderDetail;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.OrderSearchingRequest;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.SearchProduct;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.model.request.UserLogin;
import com.demosql.model.request.UserSearchingRequest;
import com.demosql.model.request.UserSignUp;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.CheckoutResponse;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.model.response.ProductSearching;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserLoginResponse;
import com.demosql.model.response.UserSignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("user/login")
    Call<ApiResponse<UserLoginResponse>> login(@Body UserLogin userLogin);

    @GET("user/current-user")
    Call<ApiResponse<UserDetailResponse>> getCurrentUser(@Header("Authorization") String token);

    @POST("user")
    Call<ApiResponse<UserSignUpResponse>> register(@Body UserSignUp userSignUp);

    // ======= Region: Customer Role =======
    @POST("shirt/getall")
    Call<ApiResponse<PagingSearchResponse<Shirt>>> getShirtList(@Header("Authorization") String token);

    @GET("order/cart")
    Call<ApiResponse<Cart>> getMyCart(@Header("Authorization") String token);

    @POST("order/addtocart")
    Call<ApiResponse<Cart>> addToCart(@Header("Authorization") String token, @Body ShirtRequest shirt);

    @POST("order/updatecart")
    Call<ApiResponse<Cart>> updateQuantity(@Header("Authorization") String token, @Body UpdateCartRequest request);

    @POST("shirt/getallbyname")
    Call<ApiResponse<PagingSearchResponse<Shirt>>> searchProduct(@Header("Authorization") String token, @Body SearchProduct request);

    @POST("order/deteleitemincart")
    Call<ApiResponse<Cart>> removeItemFromCart(@Header("Authorization") String token, @Body RemoveItemInCartRequest request);

    @DELETE("order/{id}")
    Call<ApiResponse<CheckoutResponse>> checkout(@Header("Authorization") String token, @Path("id") String id, @Query("status") int status);

    @GET("shirt/{id}")
    Call<ApiResponse<ProductSearching>> getShirtById(@Header("Authorization") String token, @Path("id") int id);
    // ======= End Region =======

    // ======= Region: Admin Role =======
    @POST("order/search")
    Call<ApiResponse<PagingSearchResponse<Order>>> searchOrder(@Header("Authorization") String token, @Body OrderSearchingRequest request);

    @GET("order-detail/getallorderdetailsbyorderid/{orderId}")
    Call<ApiResponse<PagingSearchResponse<OrderDetail>>> getOrderDetail(@Header("Authorization") String token, @Path("orderId") String orderId);

    @POST("user/search")
    Call<ApiResponse<PagingSearchResponse<UserSignUpResponse>>> searchUser(@Header("Authorization") String token, @Body UserSearchingRequest request);

    @GET("user/{id}")
    Call<ApiResponse<UserSignUpResponse>> getUserById(@Header("Authorization") String token, @Path("id") int id);
}
