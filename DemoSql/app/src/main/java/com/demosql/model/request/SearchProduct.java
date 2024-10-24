package com.demosql.model.request;

public class SearchProduct {
    private String name;
    public SearchProduct(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
