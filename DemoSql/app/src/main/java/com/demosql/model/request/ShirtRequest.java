package com.demosql.model.request;

public class ShirtRequest {

    private int shirtId;
    private int sizeId;
    private int quantity;

    public ShirtRequest(int shirtId, int sizeId, int quantity) {
        this.shirtId = shirtId;
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShirtId() {
        return shirtId;
    }

    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
    }
}
