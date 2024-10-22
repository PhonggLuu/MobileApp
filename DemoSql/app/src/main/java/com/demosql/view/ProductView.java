package com.demosql.view;

import com.demosql.model.entities.Shirt;

import java.util.List;

public interface ProductView {
    void showProducts(List<Shirt> shirts);

}
