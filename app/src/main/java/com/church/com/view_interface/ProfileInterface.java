package com.church.com.view_interface;


import com.church.com.model.BannerResponse;
import com.church.com.model.BasicResponse;
import com.church.com.model.UserProfileResponse;

public interface ProfileInterface extends BasicViewsInterface {
    void onSuccess(BasicResponse bannerResponse);
    void onGetProfileSuccess(UserProfileResponse userProfileResponse);
}
