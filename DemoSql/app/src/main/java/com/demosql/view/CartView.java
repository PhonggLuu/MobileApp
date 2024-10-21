package com.demosql.view;

import com.demosql.model.entities.Cart;
import com.demosql.model.entities.CartDetails;
import com.demosql.model.request.ShirtRequest;

import java.util.List;

public interface CartView {
    void addToCart(ShirtRequest shirt);
    void showCart(List<CartDetails> cartDetails);
}
