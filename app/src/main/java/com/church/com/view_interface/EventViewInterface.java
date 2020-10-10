package com.church.com.view_interface;


import com.church.com.model.BasicResponse;
import com.church.com.model.EventDetailResponse;
import com.church.com.model.EventResponse;

public interface EventViewInterface extends BasicViewsInterface {
    void onSuccess(EventResponse eventResponse);
    void onEventDetailSuccess(EventDetailResponse eventDetailResponse);
}
