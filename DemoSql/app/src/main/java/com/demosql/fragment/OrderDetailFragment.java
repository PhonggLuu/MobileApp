package com.demosql.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demosql.activity.MainActivity;
import com.demosql.adapter.OrderDetailAdapter;
import com.demosql.databinding.OrderDetailBinding;
import com.demosql.model.entities.OrderDetail;
import com.demosql.presenter.OrderDetailPresenter;
import com.demosql.view.OrderDetailView;

import java.util.List;

public class OrderDetailFragment extends Fragment implements OrderDetailView {
    private OrderDetailPresenter presenter;
    private OrderDetailBinding binding;
    private OrderDetailAdapter orderDetailAdapter;

    public static OrderDetailFragment newInstance(String orderId) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString("orderId", orderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new OrderDetailPresenter(getContext(), this);
        binding = OrderDetailBinding.inflate(getLayoutInflater());
        binding.recyclerViewOrderDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        String orderId = getArguments().getString("orderId");
        presenter.getOrderDetail(orderId);
        binding.goBackBtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });
        return binding.getRoot();
    }

    @Override
    public void showOrderDetail(List<OrderDetail> orderDetailList) {
        if (orderDetailAdapter == null) {
            orderDetailAdapter = new OrderDetailAdapter(getContext(), orderDetailList);
            binding.recyclerViewOrderDetail.setAdapter(orderDetailAdapter);
        } else {
            orderDetailAdapter.updateOrderDetail(orderDetailList);
        }
        updateTotal(orderDetailList);
    }

    private void updateTotal(List<OrderDetail> orderDetailList) {
        double total = 0;
        for (OrderDetail detail : orderDetailList) {
            total += detail.getPrice() * detail.getQuantity();
        }
        binding.total.setText(String.valueOf(total));
    }

}
