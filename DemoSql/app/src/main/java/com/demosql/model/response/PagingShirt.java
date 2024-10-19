package com.demosql.model.response;

import com.demosql.model.entities.Shirt;

import java.util.List;

public class PagingShirt {
    private String pageInfo;
    private String searchInfo;
    private List<Shirt> pageData;

    public PagingShirt(String pageInfo, String searchInfo, List<Shirt> shirtList) {
        this.pageInfo = pageInfo;
        this.searchInfo = searchInfo;
        this.pageData = shirtList;
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(String searchInfo) {
        this.searchInfo = searchInfo;
    }

    public List<Shirt> getShirtList() {
        return pageData;
    }

    public void setShirtList(List<Shirt> shirtList) {
        this.pageData = shirtList;
    }
}
