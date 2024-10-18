package com.demosql.network;

import com.demosql.model.dto.UserLogin;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.model.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/login")
    Call<ApiResponse<UserResponse>> login(@Body UserLogin userLogin);

    @GET("user/current-user")
    Call<ApiResponse<UserDetailResponse>> getCurrentUser(@Header("Authorization") String token);
}
