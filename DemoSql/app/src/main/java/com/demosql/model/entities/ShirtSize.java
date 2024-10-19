package com.demosql.model.entities;

import java.io.Serializable;

public class ShirtSize implements Serializable {
    private int id;
    private int shirtId;
    private String shirtName;
    private int sizeId;
    private String sizeName;
    private String sizeDescription;
    private int quantity;
    private String description;
    private boolean status;

    public ShirtSize(int id, int shirtId, String shirtName, int sizeId, String sizeName,
                     String sizeDescription, int quantity, String description, boolean status) {
        this.id = id;
        this.shirtId = shirtId;
        this.shirtName = shirtName;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.sizeDescription = sizeDescription;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
