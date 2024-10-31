package com.demosql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.databinding.ItemUserLayoutBinding;
import com.demosql.fragment.UserDetailFragment;
import com.demosql.model.response.UserSignUpResponse;

import java.util.List;

public class UserManagementAdapter extends RecyclerView.Adapter<UserManagementAdapter.UserManagementViewHolder> {
    private Context context;
    private List<UserSignUpResponse> UserList;
    public UserManagementAdapter(Context context, List<UserSignUpResponse> UserList) {
        this.context = context;
        this.UserList = UserList;
    }

    @NonNull
    @Override
    public UserManagementAdapter.UserManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemUserLayoutBinding binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new UserManagementAdapter.UserManagementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserManagementAdapter.UserManagementViewHolder holder, int i) {
        UserSignUpResponse User = UserList.get(i);
        //Set value for User information
        holder.binding.stt.setText(String.valueOf(i + 1));
        holder.binding.userName.setText(User.getUserName());
        holder.binding.email.setText(User.getEmail());
        holder.binding.role.setText(User.getRoleName());
        showStatus(User.isStatus(), holder.binding);
        holder.binding.layoutUser.setOnClickListener(v -> {
            UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(User.getId());
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, userDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    private void showStatus(boolean isStatus, ItemUserLayoutBinding binding) {
        if(!isStatus)
            binding.status.setText("Khóa");
        else
            binding.status.setText("Hoạt động");
    }

    public void updateUserManagementLayout(List<UserSignUpResponse> newUserList) {
        this.UserList.clear();
        this.UserList.addAll(newUserList);
        notifyDataSetChanged();
    }

    public static class UserManagementViewHolder extends RecyclerView.ViewHolder {
        // Use the ViewBinding class generated for the product layout
        ItemUserLayoutBinding binding;

        public UserManagementViewHolder(@NonNull ItemUserLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
