package com.demosql.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.R;
import com.demosql.model.response.ProductResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<ProductResponse> productList;
    private Context context;

    public ProductAdapter( Context context, List<ProductResponse> productList) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(productList.get(position));
        /*ProductResponse product = productList.get(position);
        holder.productName.setText(product.getName());
        *//*holder.productImage.setImageResource(product.getImage());*//*
        Picasso.get()
                .load(product.getImage()) // Đường dẫn URL
                .into(holder.productImage);*/

        /*holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_image", product.getImage());
            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
        public void bind(ProductResponse product) {
            Glide.with(itemView.getContext())
                    .load(product.getImage()) // Tải hình ảnh từ URL
                    .into(productImage);
            productName.setText(product.getName());
        }
    }
}
