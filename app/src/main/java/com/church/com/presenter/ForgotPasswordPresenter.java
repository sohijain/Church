package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BasicResponse;
import com.church.com.presenter_interface.ForgotPasswordPresenterInterface;
import com.church.com.presenter_interface.SignInPresenterInterface;
import com.church.com.view_interface.ForgotPasswordViewInterface;
import com.church.com.view_interface.SignInViewInterface;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ForgotPasswordPresenter implements ForgotPasswordPresenterInterface {

    ForgotPasswordViewInterface forgotPasswordViewInterface;

    public ForgotPasswordPresenter(ForgotPasswordViewInterface forgotPasswordViewInterface) {
        this.forgotPasswordViewInterface = forgotPasswordViewInterface;
    }


    public Observable<BasicResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .forgotPassword(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BasicResponse> getObserver() {
        return new DisposableObserver<BasicResponse>() {
            @Override
            public void onNext(@NonNull BasicResponse basicResponse) {
                forgotPasswordViewInterface.hideProgressBar();
                forgotPasswordViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                forgotPasswordViewInterface.hideProgressBar();
                e.printStackTrace();
                forgotPasswordViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                forgotPasswordViewInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void forgotPassword(Map<String, String> params) {
        forgotPasswordViewInterface.showProgressBar();
        getObservable(params).subscribeWith(getObserver());;
    }
}
