package com.demosql.model.request;

public class UserSearchingRequest {
    private int pageNum;
    private int pageSize;
    private String role;

    public UserSearchingRequest(int pageNum, int pageSize, String role) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.role = role;
    }
}
