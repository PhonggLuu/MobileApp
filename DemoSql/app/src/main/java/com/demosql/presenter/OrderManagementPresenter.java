package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.Order;
import com.demosql.model.entities.User;
import com.demosql.model.request.OrderSearchingRequest;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PageInfo;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.OrderManagementView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderManagementPresenter {
    private OrderManagementView view;
    private ApiService apiService;
    private Context context;

    public OrderManagementPresenter(Context context, OrderManagementView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
        this.context = context;
    }

    public void loadOrders(OrderSearchingRequest request) {
        apiService.searchOrder("Bearer " + User.getToken(), request).enqueue(new Callback<ApiResponse<PagingSearchResponse<Order>>>() {
            @Override
            public void onResponse(Call<ApiResponse<PagingSearchResponse<Order>>> call, Response<ApiResponse<PagingSearchResponse<Order>>> response) {
                if (response.isSuccessful()) {
                    PagingSearchResponse pagingOrderResponse  = response.body().getData();
                    if (pagingOrderResponse != null) {
                        List<Order> orderList = pagingOrderResponse.getPageData();
                        PageInfo pageInfo = pagingOrderResponse.getPageInfo();
                        view.assignPageInfo(pageInfo.getTotalPage());
                        view.showOrders(orderList);
                    } else {
                        Toast.makeText(context, "Không tìm thấy đơn hàng", Toast.LENGTH_SHORT).show();
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<PagingSearchResponse<Order>>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Error: " + throwable.getMessage());
            }
        });
    }
}
