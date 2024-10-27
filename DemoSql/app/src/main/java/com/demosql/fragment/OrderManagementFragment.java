package com.demosql.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demosql.adapter.OrderManagementAdapter;
import com.demosql.databinding.OrderManagementLayoutBinding;
import com.demosql.model.entities.Order;
import com.demosql.model.request.OrderSearchingRequest;
import com.demosql.presenter.OrderManagementPresenter;
import com.demosql.view.OrderManagementView;

import java.util.List;

public class OrderManagementFragment extends Fragment implements OrderManagementView {
    private final int PAGE_SIZE = 11;
    private final int PAYED_ORDER = 2;
    private OrderManagementPresenter presenter;
    private OrderManagementAdapter adapter;
    private OrderManagementLayoutBinding binding;

    public static OrderManagementFragment newInstance(int pageNum, int status) {
        Bundle args = new Bundle();
        OrderManagementFragment fragment = new OrderManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = OrderManagementLayoutBinding.inflate(getLayoutInflater());
        binding.recyclerviewOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        /*binding.checkoutBtn.setOnClickListener(v -> {
            //String orderId = binding.orderId.getText().toString();
            //intent.putExtra("ORDER_ID", orderId);
            Intent intent = new Intent(getActivity(), CheckoutActivity.class);
            startActivity(intent);
        });*/
        binding.pageNum.setText(String.valueOf(1));
        presenter = new OrderManagementPresenter(getContext(), this);
        //presenter.loadOrders(new OrderSearchingRequest(1, PAGE_SIZE, PAYED_ORDER));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentName", "onResume called");
        binding.pageNum.setText(String.valueOf(1));
        presenter.loadOrders(new OrderSearchingRequest(1, PAGE_SIZE, PAYED_ORDER));
    }

    @Override
    public void showOrders(List<Order> orderList) {
        if(orderList == null) {
            /*binding.imageViewEmptyCart.setVisibility(View.VISIBLE);
            binding.backToHome.setVisibility(View.VISIBLE);*/
        }
        if (adapter == null) {
            adapter = new OrderManagementAdapter(getContext(), orderList);
        } else {
            adapter.updateOrderManagementLayout(orderList);
        }
        //Khi call lại thì nó vẫn cần bind lại
        binding.recyclerviewOrder.setAdapter(adapter);
        //
        binding.prevBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            if(pageNum > 1) {
                presenter.loadOrders(new OrderSearchingRequest(pageNum - 1, PAGE_SIZE, PAYED_ORDER));
                binding.pageNum.setText(String.valueOf(pageNum - 1));
            }
        });
        binding.nextBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            int totalPage = Integer.parseInt(binding.totalPage.getText().toString());
            if(pageNum < totalPage) {
                presenter.loadOrders(new OrderSearchingRequest(pageNum + 1, PAGE_SIZE, PAYED_ORDER));
                binding.pageNum.setText(String.valueOf(pageNum + 1));
            }
        });
        binding.pageNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().trim(); // Use editable input directly
                if (query.isEmpty()) {
                    return; // Avoid parsing empty input
                }
                try {
                    //String query = s.toString();
                    int pageNum = Integer.parseInt(query);
                    if(pageNum <= Integer.parseInt(binding.totalPage.getText().toString()))
                        presenter.loadOrders(new OrderSearchingRequest(pageNum, PAGE_SIZE, PAYED_ORDER));
                } catch (NumberFormatException e) {
                    Log.e("SearchError", "Invalid number format", e);
                } catch (Exception e) {
                    Log.e("SearchError", "Error while searching products", e);
                }

            }
        });
    }

    @Override
    public void assignPageInfo(int totalPage) {
        binding.totalPage.setText(String.valueOf(totalPage));
    }
}
