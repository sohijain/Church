package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AboutUsResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private AboutUsData aboutUsData;

    public AboutUsData getAboutUsData() {
        return aboutUsData;
    }

    public void setAboutUsData(AboutUsData aboutUsData) {
        this.aboutUsData = aboutUsData;
    }
}
