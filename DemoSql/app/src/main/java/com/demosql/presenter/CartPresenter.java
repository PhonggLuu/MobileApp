package com.demosql.presenter;

import android.util.Log;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.User;
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
    private CartView view;
    private ApiService apiService;

    public CartPresenter(CartView view) {
        this.view = view;
        apiService = ApiClient.getInstance().getApiService();
    }

    public void loadCart() {

    }
}
