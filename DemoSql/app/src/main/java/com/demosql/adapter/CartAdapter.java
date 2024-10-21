package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.databinding.ItemCartLayoutBinding;
import com.demosql.databinding.ProductBinding;
import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.entities.Shirt;
import com.demosql.model.request.ShirtRequest;
import com.demosql.view.CartView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartDetails> cartDetailsList;
    private Context context;
    private CartView view;

    public CartAdapter( Context context, List<CartDetails> cartDetailsList, CartView view) {
        this.cartDetailsList = cartDetailsList;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartLayoutBinding binding = ItemCartLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CartAdapter.CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        CartDetails item = cartDetailsList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getShirtUrlImg())
                .into(holder.binding.pic);
        holder.binding.itemName.setText(item.getShirtName());
        holder.binding.itemPrice.setText(String.valueOf(item.getPrice()));
        holder.binding.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.binding.addtocartBtn.setOnClickListener(v -> {
            /*if(item.getQuantity() < Integer.parseInt(holder.binding.itemQuantity.getText().toString()) + 1) {
                Toast.makeText(this.context, "Sản phẩm đã vượt quá số lượng trong kho", Toast.LENGTH_SHORT).show();
                return;
            }*/
            ShirtRequest request = new ShirtRequest(item.getShirtId(), item.getSizeId(), 1);
            view.addToCart(request);
        });
    }

    @Override
    public int getItemCount() {
        return cartDetailsList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemCartLayoutBinding binding;

        public CartViewHolder(@NonNull ItemCartLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
