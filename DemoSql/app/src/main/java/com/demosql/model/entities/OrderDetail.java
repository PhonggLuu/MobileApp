package com.demosql.model.entities;
import lombok.Data;

@Data
public class OrderDetail {
    private int id;
    private String orderId;
    private int shirtSizeId;
    private int shirtId;
    private String shirtName;
    private String shirtUrlImg;
    private double shirtPrice;
    private String shirtDescription;
    private int sizeId;
    private String sizeName;
    private String sizeDescription;
    private int quantity;
    private double price;
    private boolean statusRating;
    private String comment; // Có thể null
    private Integer score; // Có thể null
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getShirtSizeId() {
        return shirtSizeId;
    }

    public void setShirtSizeId(int shirtSizeId) {
        this.shirtSizeId = shirtSizeId;
    }

    public int getShirtId() {
        return shirtId;
    }

    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
    }

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }

    public String getShirtUrlImg() {
        return shirtUrlImg;
    }

    public void setShirtUrlImg(String shirtUrlImg) {
        this.shirtUrlImg = shirtUrlImg;
    }

    public double getShirtPrice() {
        return shirtPrice;
    }

    public void setShirtPrice(double shirtPrice) {
        this.shirtPrice = shirtPrice;
    }

    public String getShirtDescription() {
        return shirtDescription;
    }

    public void setShirtDescription(String shirtDescription) {
        this.shirtDescription = shirtDescription;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeDescription() {
        return sizeDescription;
    }

    public void setSizeDescription(String sizeDescription) {
        this.sizeDescription = sizeDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatusRating() {
        return statusRating;
    }

    public void setStatusRating(boolean statusRating) {
        this.statusRating = statusRating;
    }
}
