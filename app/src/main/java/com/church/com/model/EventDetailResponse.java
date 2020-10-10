package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventDetailResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private EventDetailResult eventDetailResult;

    public EventDetailResult getEventDetailResult() {
        return eventDetailResult;
    }

    public void setEventDetailResult(EventDetailResult eventDetailResult) {
        this.eventDetailResult = eventDetailResult;
    }
}
