package com.demosql.view;

import com.demosql.model.entities.Shirt;

import java.util.List;

public interface ProductManagementView {
    void showProducts(List<Shirt> productList);
    void assignPageInfo(int totalPage);
}
