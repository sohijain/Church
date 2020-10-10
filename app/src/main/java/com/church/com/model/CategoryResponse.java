package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private List<CategoryData> categoryDataList=new ArrayList<>();

    public List<CategoryData> getCategoryDataList() {
        return categoryDataList;
    }

    public void setCategoryDataList(List<CategoryData> categoryDataList) {
        this.categoryDataList = categoryDataList;
    }
}
