package com.demosql.model.response;

import lombok.Data;

@Data
public class PageInfo {
    private int page;
    private int size;
    private String sort;
    private String order;
    private int totalPage;
    private int totalItem;

    public int getTotalPage() {
        return totalPage;
    }
}
