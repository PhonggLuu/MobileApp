package com.demosql.model.entities;

import java.util.List;

public class Cart {
    private int userId;
    private double totalPrice;
    private Double shipPrice; // Có thể null
    private Double deposit; // Có thể null
    private String date; // Có thể null
    private boolean refundStatus;
    private int status;
    private String id; // UUID của đơn hàng
    private List<CartDetails> orderDetails; // Danh sách chi tiết sản phẩm

    // Getter và Setter cho các thuộc tính

    public Cart(int userId, double totalPrice, Double shipPrice, Double deposit, String date, boolean refundStatus, int status, String id, List<CartDetails> orderDetails) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.shipPrice = shipPrice;
        this.deposit = deposit;
        this.date = date;
        this.refundStatus = refundStatus;
        this.status = status;
        this.id = id;
        this.orderDetails = orderDetails;
    }

    public Cart() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(Double shipPrice) {
        this.shipPrice = shipPrice;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(boolean refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<CartDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
