package com.demosql.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.databinding.ItemProductLayoutBinding;
import com.demosql.databinding.ItemProductSizeLayoutBinding;
import com.demosql.model.entities.ShirtSize;
import com.demosql.model.request.ShirtSizeRequest;
import com.demosql.presenter.ProductSizePresenter;
import com.demosql.view.ProductDetailManagementView;

import java.util.List;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ProductSizeViewHolder> {
    private Context context;
    private List<ShirtSize> sizeList;
    private ProductSizePresenter productSizePresenter;

    public ProductSizeAdapter(Context context, List<ShirtSize> sizeList, ProductDetailManagementView productDetailManagementView) {
        this.context = context;
        this.sizeList = sizeList;
        this.productSizePresenter = new ProductSizePresenter(context, productDetailManagementView);
    }

    public void updateProductSizeLayout(List<ShirtSize> newSizeList) {
        if (newSizeList == null) {
            return; // Tránh lỗi nếu newSizes là null
        }
        this.sizeList.clear();
        this.sizeList.addAll(newSizeList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductSizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemProductSizeLayoutBinding binding = ItemProductSizeLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductSizeAdapter.ProductSizeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSizeViewHolder productSizeViewHolder, int i) {
        try {
            ShirtSize size = sizeList.get(i);
            productSizeViewHolder.binding.sizeName.setText(size.getSizeName());
            productSizeViewHolder.binding.numberQuantity.setText(String.valueOf(size.getQuantity()));
            productSizeViewHolder.binding.minusSizeBtn.setOnClickListener(v -> {
                size.setQuantity(size.getQuantity() - 1);
                productSizeViewHolder.binding.numberQuantity.setText(String.valueOf(size.getQuantity()));
                ShirtSizeRequest shirtSizeRequest = new ShirtSizeRequest(size.getShirtId(), size.getSizeId(), size.getQuantity(), size.getDescription(), size.isStatus());
                productSizePresenter.updateSize(size.getSizeId(), shirtSizeRequest);
            });
            productSizeViewHolder.binding.addSizeBtn.setOnClickListener(v -> {
                size.setQuantity(size.getQuantity() + 1);
                productSizeViewHolder.binding.numberQuantity.setText(String.valueOf(size.getQuantity()));
                ShirtSizeRequest shirtSizeRequest = new ShirtSizeRequest(size.getShirtId(), size.getSizeId(), size.getQuantity(), size.getDescription(), size.isStatus());
                productSizePresenter.updateSize(size.getId(), shirtSizeRequest);
            });
            productSizeViewHolder.binding.numberQuantity.addTextChangedListener(new TextWatcher() {
                private boolean isUpdatingText = false;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Called before the text is changed
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Called as the text is being changed
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (isUpdatingText) return; // Prevent recursive calls

                    try {
                        String query = editable.toString().trim();
                        int quantity = Integer.parseInt(query);
                        size.setQuantity(quantity);

                        isUpdatingText = true; // Set the flag to prevent recursion
                        productSizeViewHolder.binding.numberQuantity.setText(String.valueOf(size.getQuantity()));
                        isUpdatingText = false; // Reset the flag

                        ShirtSizeRequest shirtSizeRequest = new ShirtSizeRequest(size.getShirtId(), size.getSizeId(), size.getQuantity(), size.getDescription(), size.isStatus());
                        productSizePresenter.updateSize(size.getId(), shirtSizeRequest);

                    } catch (NumberFormatException e) {
                        Log.e("SearchError", "Error while parsing quantity", e);
                    } catch (Exception e) {
                        Log.e("SearchError", "Error while processing quantity", e);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("ProductSizeAdapter", "Error while update size", e);
            throw e;
        }
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    public static class ProductSizeViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemProductSizeLayoutBinding binding;

        public ProductSizeViewHolder(@NonNull ItemProductSizeLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
