package com.demosql.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.demosql.adapter.ProductAdapter;
import com.demosql.databinding.ProductlistLayoutBinding;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.SearchProduct;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.ProductPresenter;
import com.demosql.view.CartView;
import com.demosql.view.ProductView;

import java.util.List;

public class ProductFragment extends Fragment implements ProductView, CartView {
    private ProductAdapter productAdapter;
    private ProductPresenter presenter;
    private CartPresenter cartPresenter;
    private ProductlistLayoutBinding binding;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new ProductPresenter(this);  // Change: Initialize ProductPresenter
        presenter.loadProducts();

        binding = ProductlistLayoutBinding.inflate(getLayoutInflater());
        binding.productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Gọi trước khi văn bản thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString();
                //SearchProduct request = new SearchProduct(1, 100, query, 1);
                //presenter.searchingProduct(request);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cartPresenter = new CartPresenter(getContext(), this);
        return binding.getRoot();
    }

    // This method will be called from MainActivity to update the product list
    public void showProducts(List<Shirt> shirts) {
        Log.d(this.getClass().getName(), "Show Product");
        if (productAdapter == null) {
            // Set up the adapter if it hasn't been initialized
            productAdapter = new ProductAdapter(getContext(), shirts, this);
            binding.productRecyclerView.setAdapter(productAdapter);
        } else {
            // Notify the adapter about data changes (e.g., if data has been updated)
            productAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addToCart(ShirtRequest shirt) {
        cartPresenter.addToCart(shirt);
    }

    @Override
    public void showCart(List<CartDetails> cartDetails) {
        Log.d(this.getClass().getName(), "Show Cart");
    }

    @Override
    public void updateCart(UpdateCartRequest request) {

    }

    @Override
    public void removeItemFromCart(RemoveItemInCartRequest request) {

    }

}
