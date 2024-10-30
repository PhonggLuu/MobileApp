package com.demosql.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.demosql.adapter.ProductManagementAdapter;
import com.demosql.adapter.ProductSizeAdapter;
import com.demosql.databinding.ProductDetailManagementLayoutBinding;
import com.demosql.model.response.ProductSearching;
import com.demosql.presenter.ProductDetailManagementPresenter;
import com.demosql.view.ProductDetailManagementView;

public class ProductDetailManagementFragment extends Fragment implements ProductDetailManagementView {
    private ProductDetailManagementPresenter presenter;
    private ProductDetailManagementLayoutBinding binding;
    private ProductSizeAdapter adapter;

    public ProductDetailManagementFragment() {

    }

    public static ProductDetailManagementFragment newInstance(int ProductId) {
        ProductDetailManagementFragment fragment = new ProductDetailManagementFragment();
        Bundle args = new Bundle();
        args.putInt("ProductId", ProductId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new ProductDetailManagementPresenter(getContext(), this);
        binding = ProductDetailManagementLayoutBinding.inflate(getLayoutInflater());
        int ProductId = getArguments().getInt("ProductId");
        presenter.getProductDetails(ProductId);
        binding.goBackBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });
        binding.updateBtn.setOnClickListener(v -> {

        });
        return binding.getRoot();
    }

    @Override
    public void showProductDetailed(ProductSearching Product) {
        Glide.with(this)
                .load(Product.getUrlImg())
                .into(binding.ProductImage);
        binding.description.setText(Product.getDescription());
        binding.ProductName.setText(Product.getName());
        binding.playerName.setText(Product.getFullName());
        binding.clubName.setText(Product.getClubName());
        binding.seasonName.setText(Product.getSessionName());
        if (adapter == null) {
            adapter = new ProductSizeAdapter(getContext(), Product.getListSize(), this);
        } else {
            adapter.updateProductSizeLayout(Product.getListSize());
        }
        binding.recyclerviewProductSize.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerviewProductSize.setAdapter(adapter);
    }
}
