package com.church.com.signup;

import java.util.Map;

import okhttp3.RequestBody;

public interface SignUpPresenterInterface {
    void signUp(Map<String, String> params);
}
