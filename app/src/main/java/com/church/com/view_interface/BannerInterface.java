package com.church.com.view_interface;


import com.church.com.model.BannerResponse;
import com.church.com.model.EventDetailResponse;
import com.church.com.model.EventResponse;

public interface BannerInterface extends BasicViewsInterface {
    void onSuccess(BannerResponse bannerResponse);
}
