package com.demosql.model.request;

public class OrderSearchingRequest {
    private int pageNum;
    private int pageSize;
    private int status;

    public OrderSearchingRequest(int pageNum, int pageSize, int status) {
        this.pageNum = pageNum;
        this.status = status;
        this.pageSize = pageSize;
    }
}
