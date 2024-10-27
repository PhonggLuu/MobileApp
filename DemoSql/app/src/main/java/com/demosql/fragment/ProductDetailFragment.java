package com.demosql.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.demosql.activity.MainActivity;
import com.demosql.databinding.ProductDetailBinding;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.model.response.ProductSearching;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.ProductDetailPresenter;
import com.demosql.view.CartView;
import com.demosql.view.ProductDetailView;

import java.util.List;

public class ProductDetailFragment extends Fragment implements ProductDetailView, CartView {
    private ProductDetailPresenter presenter;
    private ProductDetailBinding binding;
    private CartPresenter cartPresenter;

    public ProductDetailFragment() {

    }

    public static ProductDetailFragment newInstance(int productId, int sizeId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt("productId", productId);
        args.putInt("sizeId", sizeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new ProductDetailPresenter(getContext(), this);
        binding = ProductDetailBinding.inflate(getLayoutInflater());
        int productId = getArguments().getInt("productId");
        int sizeId = getArguments().getInt("sizeId");
        presenter.getProductDetails(productId);
        binding.goBackBtn.setOnClickListener(v -> {
            /*FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();*/
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        });

        binding.minusProductBtn.setOnClickListener(v -> {
            int countNumber = binding.numberProduct.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.numberProduct.getText().toString());
            if (countNumber > 1) {
                countNumber--;
                binding.numberProduct.setText(String.valueOf(countNumber));
                float total = countNumber * Float.parseFloat(binding.productPrice.getText().toString());
                binding.sumPrice.setText(String.valueOf(total));
            }
        });
        binding.addProductBtn.setOnClickListener(v -> {
            int countNumber = Integer.parseInt(binding.numberProduct.getText().toString());
            if (countNumber < Integer.parseInt(binding.maxQuantity.getText().toString())) {
                countNumber++;
                binding.numberProduct.setText(String.valueOf(countNumber));
                float total = countNumber * Float.parseFloat(binding.productPrice.getText().toString());
                binding.sumPrice.setText(String.valueOf(total));
            }
        });
        cartPresenter = new CartPresenter(getContext(), this);
        binding.addtocartBtn.setOnClickListener(v -> {
            int quantity = Integer.parseInt(binding.numberProduct.getText().toString());
            cartPresenter.addToCart(new ShirtRequest(productId, sizeId, quantity));
        });
        return binding.getRoot();
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
        binding.sumPrice.setText(String.valueOf(product.getPrice()));
        binding.maxQuantity.setText(String.valueOf(product.getListSize().get(0).getQuantity()));
    }

    @Override
    public void addToCart(ShirtRequest shirt) {

    }

    @Override
    public void showCart(List<CartDetails> cartDetails) {

    }

    @Override
    public void updateCart(UpdateCartRequest request) {

    }

    @Override
    public void removeItemFromCart(RemoveItemInCartRequest request) {

    }
}
