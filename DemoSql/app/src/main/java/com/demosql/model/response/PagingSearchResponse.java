package com.demosql.model.response;

import com.demosql.model.entities.Shirt;

import java.util.List;

import lombok.Data;

@Data
public class PagingSearchResponse<T> {
    private PageInfo pageInfo;
    private SearchInfo searchInfo;
    private List<T> pageData;

    public PagingSearchResponse(PageInfo pageInfo, List<T> pageData, SearchInfo searchInfo) {
        this.pageInfo = pageInfo;
        this.pageData = pageData;
        this.searchInfo = searchInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
