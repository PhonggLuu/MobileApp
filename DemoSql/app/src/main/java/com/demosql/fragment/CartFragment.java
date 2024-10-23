package com.demosql.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demosql.activity.CheckoutActivity;
import com.demosql.activity.MainActivity;
import com.demosql.adapter.CartAdapter;
import com.demosql.databinding.CartLayoutBinding;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.presenter.CartPresenter;
import com.demosql.view.CartView;

import java.util.List;

public class CartFragment extends Fragment implements CartView/*, CartAdapter.OnCheckoutClickListener*/ {
    private CartAdapter  cartAdapter;
    private CartPresenter presenter;
    private CartLayoutBinding binding;

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CartLayoutBinding.inflate(getLayoutInflater());
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));

        /*binding.checkoutBtn.setOnClickListener(v -> {
            //String orderId = binding.orderId.getText().toString();
            //intent.putExtra("ORDER_ID", orderId);
            Intent intent = new Intent(getActivity(), CheckoutActivity.class);
            startActivity(intent);
        });*/
        presenter = new CartPresenter(getContext(), this);
        presenter.loadCart();
        return binding.getRoot();
    }

    public void showCart(List<CartDetails> cartDetailsList) {
        //Khi giỏ hàng rỗng thì show UI khác
        if(cartDetailsList == null) {
            binding.imageViewEmptyCart.setVisibility(View.VISIBLE);
            binding.backToHome.setVisibility(View.VISIBLE);
            binding.backToHome.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), MainActivity.class);

                // Xoá tất cả các Activity trước đó
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Khởi động MainActivity
                startActivity(intent);

                // Nếu muốn kết thúc Fragment hiện tại
                getActivity().finish(); // Kết thúc Activity hiện tại nếu cần
            });

            binding.totalLayout.setVisibility(View.GONE);
            binding.recyclerViewCart.setVisibility(View.GONE);
            binding.checkoutBtn.setVisibility(View.GONE);
            binding.totalTxt.setVisibility(View.GONE);
            binding.total.setVisibility(View.GONE);
            return;
        }
        if (cartAdapter == null) {
            cartAdapter = new CartAdapter(getContext(), cartDetailsList, this);
            binding.recyclerViewCart.setAdapter(cartAdapter);
            updateTotal(cartDetailsList);
        } else {
            cartAdapter.updateCartDetails(cartDetailsList);
            updateTotal(cartDetailsList);
        }
        binding.checkoutBtn.setOnClickListener(v -> {
            //String orderId = binding.orderId.getText().toString();
            Intent intent = new Intent(getActivity(), CheckoutActivity.class);
            intent.putExtra("ORDER_ID", String.valueOf(cartDetailsList.get(0).getOrderId()));
            startActivity(intent);
        });
    }

    @Override
    public void updateCart(UpdateCartRequest request) {
        presenter.updateCart(request);
    }

    @Override
    public void removeItemFromCart(RemoveItemInCartRequest request) {
        presenter.removeItem(request);
    }

    private void updateTotal(List<CartDetails> cartDetailsList) {
        int total = 0;
        for (CartDetails detail : cartDetailsList) {
            total += detail.getPrice() * detail.getQuantity();
        }
        binding.total.setText(String.valueOf(total));
    }

    @Override
    public void addToCart(ShirtRequest shirt) {
        presenter.addToCart(shirt);
    }

    /*@Override
    public void onCheckoutClick(String orderId) {
        Intent intent = new Intent(getActivity(), CheckoutActivity.class);
        intent.putExtra("ORDER_ID", orderId);
        startActivity(intent);
    }*/
}
