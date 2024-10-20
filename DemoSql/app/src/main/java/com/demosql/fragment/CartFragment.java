package com.demosql.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.adapter.CartAdapter;
import com.demosql.adapter.ProductAdapter;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.ProductPresenter;
import com.demosql.view.CartView;

public class CartFragment extends Fragment implements CartView {

    private RecyclerView recyclerView;
    private CartAdapter  cartAdapter;
    private CartPresenter presenter;

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.cart_layout, container, false);

//        presenter = new ProductPresenter(this);  // Change: Initialize ProductPresenter
//        presenter.loadProducts();
        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Set LayoutManager

        return rootView;
    }
}
