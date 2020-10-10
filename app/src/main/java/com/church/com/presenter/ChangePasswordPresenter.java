package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BasicResponse;
import com.church.com.presenter_interface.ChangPasswordPresenterInterface;
import com.church.com.presenter_interface.ForgotPasswordPresenterInterface;
import com.church.com.view_interface.ChangePasswordViewInterface;
import com.church.com.view_interface.ForgotPasswordViewInterface;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordPresenter implements ChangPasswordPresenterInterface {

    ChangePasswordViewInterface changePasswordViewInterface;

    public ChangePasswordPresenter(ChangePasswordViewInterface changePasswordViewInterface) {
        this.changePasswordViewInterface = changePasswordViewInterface;
    }


    public Observable<BasicResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .changePassword(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BasicResponse> getObserver() {
        return new DisposableObserver<BasicResponse>() {
            @Override
            public void onNext(@NonNull BasicResponse basicResponse) {
                changePasswordViewInterface.hideProgressBar();
                changePasswordViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                changePasswordViewInterface.hideProgressBar();
                e.printStackTrace();
                changePasswordViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                changePasswordViewInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void changePassword(Map<String, String> params) {
        changePasswordViewInterface.showProgressBar();
        getObservable(params).subscribeWith(getObserver());;
    }
}
