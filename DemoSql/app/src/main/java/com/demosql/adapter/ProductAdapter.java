package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.databinding.ProductBinding;
import com.demosql.model.entities.Shirt;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Shirt> productList;
    private Context context;

    public ProductAdapter( Context context, List<Shirt> productList) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductBinding binding = ProductBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Shirt product = productList.get(position);
        holder.binding.card.setId(View.generateViewId());

        Glide.with(holder.itemView.getContext())
                .load(product.getUrlImg())
                .into(holder.binding.detailImage);
        holder.binding.detailName.setText(product.getName());
        holder.binding.detailPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ProductBinding binding;

        public ProductViewHolder(@NonNull ProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
