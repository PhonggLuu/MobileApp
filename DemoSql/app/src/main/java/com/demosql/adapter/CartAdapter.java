package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.databinding.CartLayoutBinding;
import com.demosql.databinding.ItemCartLayoutBinding;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.view.CartView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartDetails> cartDetailsList;
    private Context context;
    private CartView view;
    //private OnCheckoutClickListener listener;

    /*public void setOnCheckoutClickListener(OnCheckoutClickListener listener) {
        this.listener = listener;
    }*/

    public CartAdapter( Context context, List<CartDetails> cartDetailsList, CartView view) {
        this.cartDetailsList = cartDetailsList;
        this.context = context;
        this.view = view;
        //this.listener = listener;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartLayoutBinding binding = ItemCartLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        CartLayoutBinding cartLayoutBinding = CartLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CartAdapter.CartViewHolder(binding, cartLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        CartDetails item = cartDetailsList.get(position);

        //listener.onCheckoutClick(String.valueOf(item.getOrderId()));
//        holder.cartLayoutBinding.checkoutBtn.setOnClickListener(v -> {
//            if(listener != null) {
//                listener.onCheckoutClick(String.valueOf(item.getOrderId()));
//            }
//        });

        Glide.with(holder.itemView.getContext())
                .load(item.getShirtUrlImg())
                .into(holder.binding.pic);
        holder.binding.itemName.setText(item.getShirtName());
        holder.binding.itemPrice.setText(String.valueOf("$" + item.getPrice()));
        holder.binding.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.binding.remove.setOnClickListener(v -> {
            RemoveItemInCartRequest request = new RemoveItemInCartRequest(item.getOrderId(), item.getShirtSizeId());
            view.removeItemFromCart(request);
        });
        //holder.cartLayoutBinding.total.setText(updateTotal(cartDetailsList) + "đ");
        holder.binding.addtocartBtn.setOnClickListener(v -> {
            /*if(item.getQuantity() < Integer.parseInt(holder.binding.itemQuantity.getText().toString()) + 1) {
                Toast.makeText(this.context, "Sản phẩm đã vượt quá số lượng trong kho", Toast.LENGTH_SHORT).show();
                return;
            }*/
            UpdateCartRequest request = new UpdateCartRequest(item.getOrderId(), item.getShirtSizeId(), item.getQuantity() + 1);
            view.updateCart(request);
        });
        holder.binding.removeFromCart.setOnClickListener(v -> {
            if(item.getQuantity() > 1) {
                UpdateCartRequest request = new UpdateCartRequest(item.getOrderId(), item.getShirtSizeId(), item.getQuantity() - 1);
                view.updateCart(request);
            }
        });
    }

    public void updateCartDetails(List<CartDetails> newCartDetailsList) {
        this.cartDetailsList.clear();
        this.cartDetailsList.addAll(newCartDetailsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartDetailsList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemCartLayoutBinding binding;
        CartLayoutBinding cartLayoutBinding;

        public CartViewHolder(@NonNull ItemCartLayoutBinding binding, CartLayoutBinding cartLayoutBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.cartLayoutBinding = cartLayoutBinding;
        }
    }

    /*public interface OnCheckoutClickListener {
        void onCheckoutClick(String orderId);
    }*/
}
