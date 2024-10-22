package com.demosql.view;

public interface CheckoutView {
    void checkout(String orderId, int status);
    void navToHome();
}
