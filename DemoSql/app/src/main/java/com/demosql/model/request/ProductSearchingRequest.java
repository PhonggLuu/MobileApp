package com.demosql.model.request;

import com.google.gson.annotations.SerializedName;

public class ProductSearchingRequest {
    private int pageNum;
    private int pageSize;
    private String keyWord;

    public ProductSearchingRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public ProductSearchingRequest(int pageNum, int pageSize, String keyWord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.keyWord = keyWord;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getkeyWord() {
        return keyWord;
    }

    public void setkeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
