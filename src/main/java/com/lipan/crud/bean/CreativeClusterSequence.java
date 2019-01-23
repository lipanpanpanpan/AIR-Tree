package com.lipan.crud.bean;

public class CreativeClusterSequence {
    private Integer id;

    private String objectcluster;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectcluster() {
        return objectcluster;
    }

    public void setObjectcluster(String objectcluster) {
        this.objectcluster = objectcluster == null ? null : objectcluster.trim();
    }
}