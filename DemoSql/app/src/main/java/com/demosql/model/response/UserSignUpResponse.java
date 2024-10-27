package com.demosql.model.response;

public class UserSignUpResponse {
    private int id;
    private String userName;
    private String email;
    private String dob;
    private String address;
    private String phoneNumber;
    private String roleName;
    private String gender;
    private String imgUrl;
    private String createdDate;
    private String modifiedDate;
    private int ratingCount;
    private boolean isDelete;
    private boolean isVerify;
    private boolean status;

    public UserSignUpResponse(int id, String userName, String email, String address, String dob, String phoneNumber, String roleName, String createdDate, String gender, String imgUrl, String modifiedDate, int ratingCount, boolean isDelete, boolean isVerify, boolean status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.createdDate = createdDate;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.modifiedDate = modifiedDate;
        this.ratingCount = ratingCount;
        this.isDelete = isDelete;
        this.isVerify = isVerify;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
