package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demosql.databinding.ItemOrderDetailLayoutBinding;
import com.demosql.model.entities.OrderDetail;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private List<OrderDetail> OrderDetailList;
    private Context context;

    public OrderDetailAdapter( Context context, List<OrderDetail> OrderDetailList) {
        this.OrderDetailList = OrderDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderDetailLayoutBinding binding = ItemOrderDetailLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderDetailAdapter.OrderDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderDetailViewHolder holder, int position) {
        OrderDetail item = OrderDetailList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getShirtUrlImg())
                .into(holder.binding.pic);
        holder.binding.itemName.setText(item.getShirtName());
        holder.binding.itemPrice.setText(String.valueOf(item.getPrice()));
        holder.binding.itemQuantity.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return OrderDetailList.size();
    }

    public void updateOrderDetail(List<OrderDetail> newOrderDetailList) {
        this.OrderDetailList.clear();
        this.OrderDetailList.addAll(newOrderDetailList);
        notifyDataSetChanged();
    }

    public static class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemOrderDetailLayoutBinding binding;

        public OrderDetailViewHolder(@NonNull ItemOrderDetailLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
