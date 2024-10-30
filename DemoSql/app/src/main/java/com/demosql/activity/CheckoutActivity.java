package com.demosql.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.demosql.adapter.CartAdapter;
import com.demosql.databinding.CartLayoutBinding;
import com.demosql.databinding.CheckoutBinding;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.request.RemoveItemInCartRequest;
import com.demosql.model.request.ShirtRequest;
import com.demosql.model.request.UpdateCartRequest;
import com.demosql.presenter.CartPresenter;
import com.demosql.presenter.CheckoutPresenter;
import com.demosql.view.CartView;
import com.demosql.view.CheckoutView;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements CheckoutView {
    private CheckoutBinding binding;
    private CheckoutPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CheckoutBinding.inflate(getLayoutInflater());
        binding.backToHome.setOnClickListener(v -> {
            String orderId = getIntent().getStringExtra("ORDER_ID");
            presenter.checkout(orderId, 2);
            navToHome();
        });
        setContentView(binding.getRoot());

        presenter = new CheckoutPresenter(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.checkout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void navToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
