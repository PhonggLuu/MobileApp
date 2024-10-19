package com.demosql.model.entities;

import java.io.Serializable;
import java.util.List;

public class Shirt implements Serializable {
    private int id;
    private String fullName;
    private int clubId;
    private String clubName;
    private String clubEstablishedYear;
    private String clubLogo;
    private String name;
    private int number;
    private double price;
    private String date;
    private String description;
    private String urlImg;
    private int status;
    private List<ShirtSize> listSize;

    // Constructor
    public Shirt(int id, String fullName, int clubId, String clubName, String clubEstablishedYear,
                  String clubLogo, String name, int number, double price, String date,
                  String description, String urlImg, int status, List<ShirtSize> listSize) {
        this.id = id;
        this.fullName = fullName;
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubEstablishedYear = clubEstablishedYear;
        this.clubLogo = clubLogo;
        this.name = name;
        this.number = number;
        this.price = price;
        this.date = date;
        this.description = description;
        this.urlImg = urlImg;
        this.status = status;
        this.listSize = listSize;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubEstablishedYear() {
        return clubEstablishedYear;
    }

    public void setClubEstablishedYear(String clubEstablishedYear) {
        this.clubEstablishedYear = clubEstablishedYear;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<ShirtSize> getListSize() {
        return listSize;
    }

    public void setListSize(List<ShirtSize> listSize) {
        this.listSize = listSize;
    }
}
