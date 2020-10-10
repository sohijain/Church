package com.church.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventResponse extends BasicResponse {
    @SerializedName("result")
    @Expose
    private List<EventData> eventData=new ArrayList<>();

}
