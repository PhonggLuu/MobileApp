package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.User;
import com.demosql.model.entities.User;
import com.demosql.model.request.UserSearchingRequest;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PageInfo;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.UserManagementView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManagementPresenter {
    private UserManagementView view;
    private ApiService apiService;
    private Context context;

    public UserManagementPresenter(Context context, UserManagementView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
        this.context = context;
    }

    public void loadUsers(UserSearchingRequest request) {
        apiService.searchUser("Bearer " + User.getToken(), request).enqueue(new Callback<ApiResponse<PagingSearchResponse<UserSignUpResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<PagingSearchResponse<UserSignUpResponse>>> call, Response<ApiResponse<PagingSearchResponse<UserSignUpResponse>>> response) {
                if (response.isSuccessful()) {
                    PagingSearchResponse pagingUserResponse  = response.body().getData();
                    if (pagingUserResponse != null) {
                        List<UserSignUpResponse> UserList = pagingUserResponse.getPageData();
                        PageInfo pageInfo = pagingUserResponse.getPageInfo();
                        view.assignPageInfo(pageInfo.getTotalPage());
                        view.showUsers(UserList);
                    } else {
                        Toast.makeText(context, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<PagingSearchResponse<UserSignUpResponse>>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Error: " + throwable.getMessage());
            }
        });
    }
}
