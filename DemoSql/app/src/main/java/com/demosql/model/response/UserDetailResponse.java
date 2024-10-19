package com.demosql.model.response;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDetailResponse implements Serializable {
    private int id;
    private String userName;
    private String email;
    private String  dob;
    private String address;
    private String phoneNumber;
    private String roleName;
    private String gender;
    private String imgUrl;
    private String  createdDate;
    private String  modifiedDate;
    private Integer ratingCount;
    private boolean isDelete;
    private boolean isVerify;
    private boolean status;

    public UserDetailResponse(int id, boolean status, boolean isVerify, boolean isDelete, Integer ratingCount, String modifiedDate, String createdDate, String imgUrl, String gender, String roleName, String phoneNumber, String address, String  dob, String email, String userName) {
        this.id = id;
        this.status = status;
        this.isVerify = isVerify;
        this.isDelete = isDelete;
        this.ratingCount = ratingCount;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
        this.imgUrl = imgUrl;
        this.gender = gender;
        this.roleName = roleName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.userName = userName;
    }

    public UserDetailResponse(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String  getDob() {
        return dob;
    }

    public void setDob(String  dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String  getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String  createdDate) {
        this.createdDate = createdDate;
    }

    public String  getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String  modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }
}

