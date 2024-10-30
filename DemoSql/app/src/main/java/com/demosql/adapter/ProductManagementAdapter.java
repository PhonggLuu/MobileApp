package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.R;
import com.demosql.databinding.ItemProductLayoutBinding;
import com.demosql.fragment.ProductDetailManagementFragment;
import com.demosql.model.entities.Shirt;

import java.text.DecimalFormat;
import java.util.List;

public class ProductManagementAdapter extends RecyclerView.Adapter<ProductManagementAdapter.ProductManagementViewHolder> {
    private Context context;
    private List<Shirt> ProductList;
    public ProductManagementAdapter(Context context, List<Shirt> ProductList) {
        this.context = context;
        this.ProductList = ProductList;
    }

    @NonNull
    @Override
    public ProductManagementAdapter.ProductManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemProductLayoutBinding binding = ItemProductLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductManagementAdapter.ProductManagementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductManagementAdapter.ProductManagementViewHolder holder, int i) {
        Shirt Product = ProductList.get(i);
        //Set value for Product information
        Glide.with(holder.itemView.getContext())
                .load(Product.getUrlImg())
                .into(holder.binding.productImage);
        holder.binding.stt.setText(String.valueOf(i + 1));
        holder.binding.productName.setText(Product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
        String formattedPrice = decimalFormat.format(Product.getPrice());
        holder.binding.price.setText(formattedPrice);
        holder.binding.status.setText(Product.getStatus() > 0 ? "Còn hàng" : "Hết hàng");
        holder.binding.layoutProduct.setOnClickListener(v -> {
            ProductDetailManagementFragment ProductDetailFragment = ProductDetailManagementFragment.newInstance(Product.getId());
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProductDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public void updateProductManagementLayout(List<Shirt> newProductList) {
        this.ProductList.clear();
        this.ProductList.addAll(newProductList);
        notifyDataSetChanged();
    }

    public static class ProductManagementViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemProductLayoutBinding binding;

        public ProductManagementViewHolder(@NonNull ItemProductLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
