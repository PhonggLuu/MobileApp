package com.demosql.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.adapter.ProductAdapter;
import com.demosql.model.entities.Shirt;
import com.demosql.presenter.ProductPresenter;
import com.demosql.view.ProductView;

import java.util.List;

public class ProductFragment extends Fragment implements ProductView {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductPresenter presenter;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.productlist_layout, container, false);

        presenter = new ProductPresenter(this);  // Change: Initialize ProductPresenter
        presenter.loadProducts();
        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Set LayoutManager

        return rootView;
    }

    // This method will be called from MainActivity to update the product list
    public void showProducts(List<Shirt> shirts) {
        Log.d(this.getClass().getName(), "Show Product");
        if (productAdapter == null) {
            // Set up the adapter if it hasn't been initialized
            productAdapter = new ProductAdapter(getContext(), shirts);
            recyclerView.setAdapter(productAdapter);
        } else {
            // Notify the adapter about data changes (e.g., if data has been updated)
            productAdapter.notifyDataSetChanged();
        }
    }
}
