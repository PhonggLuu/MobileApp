package com.demosql.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.activity.CheckoutActivity;
import com.demosql.adapter.CartAdapter;
import com.demosql.adapter.ProductAdapter;
import com.demosql.databinding.CartLayoutBinding;
import com.demosql.databinding.ItemCartLayoutBinding;
import com.demosql.databinding.ProductlistLayoutBinding;
import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.response.UserDetailResponse;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.ProductPresenter;
import com.demosql.view.CartView;

import java.util.List;

public class CartFragment extends Fragment implements CartView {
    private CartAdapter  cartAdapter;
    private CartPresenter presenter;
    private CartLayoutBinding binding;
    private ItemCartLayoutBinding childBinding;

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CartLayoutBinding.inflate(getLayoutInflater());
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));

        childBinding = ItemCartLayoutBinding.inflate(getLayoutInflater());

        presenter = new CartPresenter(getContext(), this);
        presenter.loadCart();

        binding.checkoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CheckoutActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }

    public void showCart(List<CartDetails> cartDetailsList) {
        Log.d(this.getClass().getName(), "Show Product");
        if (cartAdapter == null) {
            // Set up the adapter if it hasn't been initialized
            cartAdapter = new CartAdapter(getContext(), cartDetailsList, this);
            binding.recyclerViewCart.setAdapter(cartAdapter);
            int total = 0;
            for (CartDetails detail: cartDetailsList) {
                total += detail.getPrice() * detail.getQuantity();
            }
            binding.total.setText(String.valueOf(total));
        } else {
            // Notify the adapter about data changes (e.g., if data has been updated)
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addToCart(ShirtRequest shirt) {
        presenter.addToCart(shirt);
    }
}
