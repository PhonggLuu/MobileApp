package com.demosql.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.demosql.model.entities.OrderDetail;
import com.demosql.model.entities.User;
import com.demosql.model.response.ApiResponse;
import com.demosql.model.response.PagingSearchResponse;
import com.demosql.model.response.ProductSearching;
import com.demosql.network.ApiClient;
import com.demosql.network.ApiService;
import com.demosql.view.OrderDetailView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresenter {
    private Context context;
    private OrderDetailView view;
    private ApiService apiService;
    public OrderDetailPresenter(Context context, OrderDetailView view) {
        this.context = context;
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }
    public void getOrderDetail(String orderId) {
        apiService.getOrderDetail("Bearer " + User.getToken(), orderId).enqueue(new Callback<ApiResponse<PagingSearchResponse<OrderDetail>>>() {
            @Override
            public void onResponse(Call<ApiResponse<PagingSearchResponse<OrderDetail>>> call, Response<ApiResponse<PagingSearchResponse<OrderDetail>>> response) {
                if (response.isSuccessful()) {
                    PagingSearchResponse pagingSearchResponse  = response.body().getData();
                    List<OrderDetail> orderDetailList = pagingSearchResponse.getPageData();
                    if (orderDetailList != null) {
                        //Toast.makeText(context, "Chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
                        view.showOrderDetail(orderDetailList);
                    } else {
                        Log.e(this.getClass().getName(), "Error: Get profile data failed");
                    }
                } else {
                    Log.e(this.getClass().getName(), "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<PagingSearchResponse<OrderDetail>>> call, Throwable throwable) {
                Log.e(this.getClass().getName(), "Get produc failed");
            }
        });
    }
}
