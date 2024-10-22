package com.demosql.model.response;

import java.util.List;

public class ProductSearchingPaging {
    private String pageInfo;
    private String searchInfo;
    private List<ProductSearching> pageData;

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

    public List<ProductSearching> getPageData() {
        return pageData;
    }

    public void setPageData(List<ProductSearching> pageData) {
        this.pageData = pageData;
    }
}
