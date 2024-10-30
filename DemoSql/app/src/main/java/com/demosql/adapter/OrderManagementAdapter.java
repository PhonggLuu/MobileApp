package com.demosql.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.databinding.ItemOrderLayoutBinding;
import com.demosql.fragment.OrderDetailFragment;
import com.demosql.model.entities.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class OrderManagementAdapter extends RecyclerView.Adapter<OrderManagementAdapter.OrderManagementViewHolder>{
    private Context context;
    private List<Order> orderList;
    public OrderManagementAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemOrderLayoutBinding binding = ItemOrderLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderManagementAdapter.OrderManagementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderManagementViewHolder holder, int i) {
        Order order = orderList.get(i);
        //Set value for order information
        holder.binding.stt.setText(String.valueOf(i + 1));
        holder.binding.orderId.setText(order.getId());
        if(order.getUserUserName() != null)
            holder.binding.userName.setText(order.getUserUserName());
        showPrice(order.getTotalPrice(), holder.binding);
        showStatus(order.getStatus(), holder.binding);
        showDate(order.getDate(), holder.binding);
        holder.binding.layoutOrder.setOnClickListener(v -> {
            OrderDetailFragment orderDetailFragment = OrderDetailFragment.newInstance(order.getId());
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, orderDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    private void showDate(String inputDate, ItemOrderLayoutBinding binding) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            try {
                LocalDate date = LocalDate.parse(inputDate.substring(0, 10));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = date.format(formatter);

                binding.dates.setText(formattedDate);
            } catch (DateTimeParseException e) {
                throw e;
            }
        }
        binding.dates.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
    }

    private void showPrice(int price, ItemOrderLayoutBinding binding) {
        String priceStr = String.valueOf(price);
        binding.price.setText(priceStr);
        binding.price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
    }

    private void showStatus(int status, ItemOrderLayoutBinding binding) {
        if(status == 1)
            binding.status.setText("Trong giỏ hàng");
        else if(status == 2)
            binding.status.setText("Đã thanh toán");
    }

    public void updateOrderManagementLayout(List<Order> newOrderList) {
        this.orderList.clear();
        this.orderList.addAll(newOrderList);
        notifyDataSetChanged();
    }

    public static class OrderManagementViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemOrderLayoutBinding binding;

        public OrderManagementViewHolder(@NonNull ItemOrderLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
