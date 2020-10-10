package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private UserData userData;

    public UserData getLoginResult() {
        return userData;
    }

    public void setLoginResult(UserData userData) {
        this.userData = userData;
    }
}
