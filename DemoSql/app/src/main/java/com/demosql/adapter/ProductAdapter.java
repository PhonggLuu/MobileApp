package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.R;
import com.demosql.databinding.ProductBinding;
import com.demosql.fragment.CartFragment;
import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.ShirtSize;
import com.demosql.model.request.ShirtRequest;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.LoginPresenter;
import com.demosql.view.CartView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Shirt> productList;
    private Context context;
    private CartView view;

    public ProductAdapter( Context context, List<Shirt> productList, CartView view) {
        this.productList = productList;
        this.context = context;
        this.view = view;
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

        Glide.with(holder.itemView.getContext())
                .load(product.getUrlImg())
                .into(holder.binding.detailImage);
        holder.binding.detailName.setText(product.getName());
        holder.binding.detailPrice.setText(String.valueOf(product.getPrice()));
        holder.binding.addtocartBtn.setOnClickListener(v -> {
            /*if(product.getListSize().size() == 0) {
                Toast.makeText(this.context, "Sản phẩm hiện đang hết hàng", Toast.LENGTH_SHORT).show();
                return;
            }
            if(product.getListSize().get(0).getQuantity() <= 0) {
                Toast.makeText(this.context, "Sản phẩm hiện đang hết hàng", Toast.LENGTH_SHORT).show();
                return;
            }*/
            ShirtRequest request = new ShirtRequest(product.getId(), product.getListSize().get(0).getSizeId(), 1);
            view.addToCart(request);
        });
        //holder.binding.total
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
