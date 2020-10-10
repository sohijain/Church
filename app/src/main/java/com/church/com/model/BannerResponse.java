package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private List<BannerData> bannerData=new ArrayList<>();

    public List<BannerData> getBannerData() {
        return bannerData;
    }

    public void setBannerData(List<BannerData> bannerData) {
        this.bannerData = bannerData;
    }
}
