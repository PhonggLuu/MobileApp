package com.demosql.network;

import com.demosql.model.dto.UserLogin;
import com.demosql.model.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/login")
    Call<UserResponse> login(@Body UserLogin userLogin);
}
