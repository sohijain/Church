package com.church.com.view_interface;


import com.church.com.model.CategoryResponse;
import com.church.com.model.EventDetailResponse;
import com.church.com.model.EventResponse;

public interface CategoryViewInterface extends BasicViewsInterface {
    void onSuccess(CategoryResponse categoryResponse);
}
