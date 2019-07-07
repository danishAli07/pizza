package com.synuptest.model;

public class ExcludeItemModel {

    private String parentId;
    private String childId;
    private String subChildId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getSubChildId() {
        return subChildId;
    }

    public void setSubChildId(String subChildId) {
        this.subChildId = subChildId;
    }
}
