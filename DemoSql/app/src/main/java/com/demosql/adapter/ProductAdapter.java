package com.demosql.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.R;
import com.demosql.activity.ProductDetailActivity;
import com.demosql.databinding.ProductBinding;
import com.demosql.fragment.CartFragment;
import com.demosql.fragment.ProductDetailFragment;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.entities.ShirtSize;
import com.demosql.model.request.ShirtRequest;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.LoginPresenter;
import com.demosql.view.CartView;
import com.demosql.view.ProductDetailView;
import com.demosql.view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Shirt> productList;
    private Context context;
    private CartView view;

    public ProductAdapter(Context context, List<Shirt> productList, CartView view) {
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
            }*/
            if(product.getListSize().get(0).getQuantity() <= 0) {
                Toast.makeText(this.context, "Sản phẩm hiện đang hết hàng", Toast.LENGTH_SHORT).show();
                return;
            }
            ShirtRequest request = new ShirtRequest(product.getId(), product.getListSize().get(0).getSizeId(), 1);
            view.addToCart(request);
        });
        /*holder.binding.detailImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailFragment.class);
            //productDetailView.showProductDetails(product.getId());
            intent.putExtra("productId", product.getId());
            context.startActivity(intent);
        });*/
        holder.binding.detailImage.setOnClickListener(v -> {
            ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance(product.getId(), product.getListSize().get(0).getSizeId());
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, productDetailFragment) // R.id.fragment_container là ID của container trong layout
                    .addToBackStack(null) // Để có thể quay lại
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ProductBinding binding;

        public ProductViewHolder(@NonNull ProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void updateCartDetails(List<Shirt> newShirtsList) {
        this.productList.clear();
        this.productList.addAll(newShirtsList);
        notifyDataSetChanged();
    }
}
