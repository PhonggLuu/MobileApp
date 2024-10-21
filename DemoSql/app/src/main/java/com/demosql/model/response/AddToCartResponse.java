package com.demosql.model.response;

public class AddToCartResponse {
    private int userId;
    private int totalPrice;
    private int id;

    public AddToCartResponse(int userId, int totalPrice, int id) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
