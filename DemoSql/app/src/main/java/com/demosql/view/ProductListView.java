package com.demosql.view;
import com.demosql.model.response.ProductResponse;

import java.util.List;

public interface ProductListView {
    void displayItems(List<ProductResponse> items);
}