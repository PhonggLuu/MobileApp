/*
package com.demosql.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demosql.R;
import com.demosql.adapter.ProductAdapter;
import com.demosql.model.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<ProductResponse> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Giả định bạn đã có danh sách sản phẩm
        productList = new ArrayList<>();
        loadProducts(); // Tạo phương thức để tải sản phẩm

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);
    }
}
*/
