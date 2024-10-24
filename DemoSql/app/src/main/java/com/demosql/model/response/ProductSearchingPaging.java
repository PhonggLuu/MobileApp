package com.demosql.model.response;

import com.demosql.model.entities.Shirt;

import java.util.List;

public class ProductSearchingPaging {
    private String pageInfo;
    private SearchInfo searchInfo;
    private List<Shirt> pageData;

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public List<Shirt> getPageData() {
        return pageData;
    }

    public void setPageData(List<Shirt> pageData) {
        this.pageData = pageData;
    }
}
