package com.demosql.model.request;

public class UpdateCartRequest {
    private String orderId;
    private int shirtSizeId;
    private int quantity;

    public UpdateCartRequest(String orderId, int shirtSizeId, int quantity) {
        this.orderId = orderId;
        this.shirtSizeId = shirtSizeId;
        this.quantity = quantity;
    }
}
