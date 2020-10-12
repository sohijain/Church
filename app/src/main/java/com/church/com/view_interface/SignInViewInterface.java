package com.church.com.view_interface;


import com.church.com.model.BasicResponse;
import com.church.com.model.LoginResponse;

public interface SignInViewInterface extends BasicViewsInterface {
    void onSuccess(LoginResponse basicResponse);
}
