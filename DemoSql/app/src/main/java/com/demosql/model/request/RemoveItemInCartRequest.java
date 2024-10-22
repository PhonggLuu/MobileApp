package com.demosql.model.request;

public class RemoveItemInCartRequest {
    private String orderId;
    private int shirtSizeId;

    public RemoveItemInCartRequest(String orderId, int shirtSizeId) {
        this.orderId = orderId;
        this.shirtSizeId = shirtSizeId;
    }
}
