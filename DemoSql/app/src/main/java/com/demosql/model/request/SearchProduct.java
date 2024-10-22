package com.demosql.model.request;

public class SearchProduct {
    private int pageNum;
    private int pageSize;
    private String keyWord;
    private int status;

    public SearchProduct(int pageNum, int pageSize, String keyWord, int status) {
        this.pageNum = pageNum;
        this.status = status;
        this.keyWord = keyWord;
        this.pageSize = pageSize;
    }
}
