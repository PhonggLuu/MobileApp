package com.demosql.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demosql.adapter.ProductManagementAdapter;
import com.demosql.databinding.ProductManagementLayoutBinding;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.ProductSearchingRequest;
import com.demosql.presenter.ProductManagementPresenter;
import com.demosql.view.ProductManagementView;

import java.util.List;

public class ProductManagementFragment extends Fragment implements ProductManagementView {
    private final int PAGE_SIZE = 10;
    private ProductManagementPresenter presenter;
    private ProductManagementAdapter adapter;
    private ProductManagementLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ProductManagementLayoutBinding.inflate(getLayoutInflater());
        binding.recyclerviewProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.query.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        presenter = new ProductManagementPresenter(getContext(), this);
        binding.query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Gọi trước khi văn bản thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String query = editable.toString().trim();
                    presenter.loadProducts(new ProductSearchingRequest(binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString()), PAGE_SIZE, query));
                } catch (Exception e) {
                    Log.e("SearchError", "Error while searching products", e);
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentName", "onResume called");
        binding.pageNum.setText(String.valueOf(1));
        presenter.loadProducts(new ProductSearchingRequest(1, PAGE_SIZE));
    }

    @Override
    public void showProducts(List<Shirt> ProductList) {
        if(ProductList == null) {
            /*binding.imageViewEmptyCart.setVisibility(View.VISIBLE);
            binding.backToHome.setVisibility(View.VISIBLE);*/
        }
        if (adapter == null) {
            adapter = new ProductManagementAdapter(getContext(), ProductList);
        } else {
            adapter.updateProductManagementLayout(ProductList);
        }
        //Khi call lại thì nó vẫn cần bind lại
        binding.recyclerviewProduct.setAdapter(adapter);
        //
        binding.prevProductBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            if(pageNum > 1) {
                presenter.loadProducts(new ProductSearchingRequest(pageNum - 1, PAGE_SIZE, binding.query.getText().toString()));
                binding.pageNum.setText(String.valueOf(pageNum - 1));
            }
            else {
                presenter.loadProducts(new ProductSearchingRequest(Integer.parseInt(binding.totalPage.getText().toString()), PAGE_SIZE, binding.query.getText().toString()));
                binding.pageNum.setText(String.valueOf(binding.totalPage));
            }
        });
        binding.nextProductBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            int totalPage = Integer.parseInt(binding.totalPage.getText().toString());
            if(pageNum < totalPage) {
                presenter.loadProducts(new ProductSearchingRequest(pageNum + 1, PAGE_SIZE, binding.query.getText().toString()));
                binding.pageNum.setText(String.valueOf(pageNum + 1));
            }
            else {
                presenter.loadProducts(new ProductSearchingRequest(1, PAGE_SIZE, binding.query.getText().toString()));
                binding.pageNum.setText(String.valueOf(1));
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
                        presenter.loadProducts(new ProductSearchingRequest(pageNum, PAGE_SIZE, binding.query.getText().toString()));
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
