package com.demosql.model.request;

public class ShirtSizeRequest {
    private int shirtId;
    private int sizeId;
    private int quantity;
    private String description;
    private boolean status;

    public ShirtSizeRequest(int shirtId, int sizeId, int quantity, String description, boolean status) {
        this.shirtId = shirtId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public int getShirtId() {
        return shirtId;
    }

    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
