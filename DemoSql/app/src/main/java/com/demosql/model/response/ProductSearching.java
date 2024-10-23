package com.demosql.model.response;

import com.demosql.model.entities.ShirtSize;

import java.util.List;

public class ProductSearching {
    private int id;
    private int typeShirtId;
    private String typeShirtName;
    private int sessonId;
    private String sessionName;
    private int playerId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeShirtId() {
        return typeShirtId;
    }

    public void setTypeShirtId(int typeShirtId) {
        this.typeShirtId = typeShirtId;
    }

    public String getTypeShirtName() {
        return typeShirtName;
    }

    public void setTypeShirtName(String typeShirtName) {
        this.typeShirtName = typeShirtName;
    }

    public int getSessonId() {
        return sessonId;
    }

    public void setSessonId(int sessonId) {
        this.sessonId = sessonId;
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

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
