package com.demosql.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demosql.adapter.UserManagementAdapter;
import com.demosql.databinding.UserManagementLayoutBinding;
import com.demosql.model.request.UserSearchingRequest;
import com.demosql.model.response.UserSignUpResponse;
import com.demosql.presenter.UserManagementPresenter;
import com.demosql.view.UserManagementView;

import java.util.List;

public class UserManagementFragment extends Fragment implements UserManagementView {
    private final int PAGE_SIZE = 10;
    private final String[] ROLE = {"User", "Staff", "Manager", "Admin"};
    private UserManagementPresenter presenter;
    private UserManagementAdapter adapter;
    private UserManagementLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = UserManagementLayoutBinding.inflate(getLayoutInflater());
        binding.recyclerviewUser.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                ROLE
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.roleSpinner.setAdapter(spinnerAdapter); // Gán adapter cho Spinner
        binding.roleSpinner.setSelection(0);
        binding.roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRole = ROLE[position];
                //String selectedItem = parent.getItemAtPosition(position).toString();
                presenter.loadUsers(new UserSearchingRequest(1, PAGE_SIZE, selectedRole));
                Toast.makeText(getContext(), "Selected Role: " + selectedRole, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có lựa chọn nào
            }
        });
        presenter = new UserManagementPresenter(getContext(), this);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentName", "onResume called");
        binding.pageNum.setText(String.valueOf(1));
        presenter.loadUsers(new UserSearchingRequest(1, PAGE_SIZE, "User"));
    }

    @Override
    public void showUsers(List<UserSignUpResponse> UserList) {
        if(UserList == null) {
            /*binding.imageViewEmptyCart.setVisibility(View.VISIBLE);
            binding.backToHome.setVisibility(View.VISIBLE);*/
        }
        if (adapter == null) {
            adapter = new UserManagementAdapter(getContext(), UserList);
        } else {
            adapter.updateUserManagementLayout(UserList);
        }
        //Khi call lại thì nó vẫn cần bind lại
        binding.recyclerviewUser.setAdapter(adapter);
        //
        binding.prevUserBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            if(pageNum > 1) {
                presenter.loadUsers(new UserSearchingRequest(pageNum - 1, PAGE_SIZE, binding.roleSpinner.getSelectedItem().toString()));
                binding.pageNum.setText(String.valueOf(pageNum - 1));
            }
        });
        binding.nextUserBtn.setOnClickListener(v -> {
            int pageNum = binding.pageNum.getText().toString().isEmpty() ? 1 : Integer.parseInt(binding.pageNum.getText().toString());
            int totalPage = Integer.parseInt(binding.totalPage.getText().toString());
            if(pageNum < totalPage) {
                presenter.loadUsers(new UserSearchingRequest(pageNum + 1, PAGE_SIZE, binding.roleSpinner.getSelectedItem().toString()));
                binding.pageNum.setText(String.valueOf(pageNum + 1));
            }
        });
        binding.pageNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().trim(); // Use editable input directly
                if (query.isEmpty()) {
                    return; // Avoid parsing empty input
                }
                try {
                    //String query = s.toString();
                    int pageNum = Integer.parseInt(query);
                    if(pageNum <= Integer.parseInt(binding.totalPage.getText().toString()))
                        presenter.loadUsers(new UserSearchingRequest(pageNum, PAGE_SIZE, binding.roleSpinner.getSelectedItem().toString()));
                } catch (NumberFormatException e) {
                    Log.e("SearchError", "Invalid number format", e);
                } catch (Exception e) {
                    Log.e("SearchError", "Error while searching products", e);
                }

            }
        });
    }

    @Override
    public void assignPageInfo(int totalPage) {
        binding.totalPage.setText(String.valueOf(totalPage));
    }
}
