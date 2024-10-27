package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.demosql.databinding.ProductDetailBinding;
import com.demosql.databinding.SignupLayoutBinding;
import com.demosql.model.response.ProductSearching;
import com.demosql.presenter.ProductDetailPresenter;
import com.demosql.presenter.SignUpPresenter;
import com.demosql.view.ProductDetailView;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {
    private ProductDetailPresenter presenter;
    private ProductDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int productId = getIntent().getIntExtra("productId", -1);
        presenter.getProductDetails(productId);

        presenter = new ProductDetailPresenter(this);
    }

    @Override
    public void showProductDetailed(ProductSearching product) {
        Glide.with(this)
                .load(product.getUrlImg())
                .into(binding.productImage);
        binding.productName.setText(product.getName());
        binding.productPrice.setText(String.valueOf(product.getPrice()));
        binding.playerName.setText(product.getFullName());
        binding.clubName.setText(product.getClubName());
        binding.seasonName.setText(product.getSessionName());
        binding.description.setText(product.getDescription());
    }
}
