package com.demosql.model.entities;

public class ProductDetail {
    private String properties;
    private String info;

    public ProductDetail(String properties, String info) {
        this.properties = properties;
        this.info = info;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
