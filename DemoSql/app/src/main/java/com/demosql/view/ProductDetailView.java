package com.demosql.view;

import com.demosql.model.response.ProductSearching;

public interface ProductDetailView {
    void showProductDetails(int productId);
    void showProductDetailed(ProductSearching product);
}
