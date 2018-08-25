package com.example.RESTfulServices.models;

public class Airport {

    private Long resourceId;
    private String name;
    private String code;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
