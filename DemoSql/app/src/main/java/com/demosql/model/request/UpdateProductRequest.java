package com.demosql.model.request;

public class UpdateProductRequest {
    private int typeShirtId;
    private int playerId;
    private String name;
    private int number;
    private double price;
    private String description;
    private String urlImg;
    private int status;

    public UpdateProductRequest(int typeShirtId, int playerId, String name, int number, double price, String description, String urlImg, int status) {
        this.typeShirtId = typeShirtId;
        this.playerId = playerId;
        this.name = name;
        this.number = number;
        this.price = price;
        this.description = description;
        this.urlImg = urlImg;
        this.status = status;
    }

    public int getTypeShirtId() {
        return typeShirtId;
    }

    public void setTypeShirtId(int typeShirtId) {
        this.typeShirtId = typeShirtId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
