package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BannerResponse;
import com.church.com.model.BasicResponse;
import com.church.com.model.UserProfileResponse;
import com.church.com.presenter_interface.BannerPresenterInterface;
import com.church.com.presenter_interface.ProfilePresenterInterface;
import com.church.com.view_interface.BannerInterface;
import com.church.com.view_interface.ProfileInterface;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter implements ProfilePresenterInterface {

    ProfileInterface profileInterface;

    public ProfilePresenter(ProfileInterface profileInterface) {
        this.profileInterface = profileInterface;
    }


    public Observable<UserProfileResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getProfile(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<UserProfileResponse> getObserver() {
        return new DisposableObserver<UserProfileResponse>() {
            @Override
            public void onNext(@NonNull UserProfileResponse basicResponse) {
                profileInterface.hideProgressBar();
                profileInterface.onGetProfileSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                profileInterface.hideProgressBar();
                e.printStackTrace();
                profileInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                profileInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void getProfile(Map<String, String> params) {
        profileInterface.showProgressBar();
        getObservable(params).subscribeWith(getObserver());
    }

    @Override
    public void updateProfile(Map<String, String> params) {
        profileInterface.showProgressBar();
        getObservable1(params).subscribeWith(getObserver1());
    }
    public Observable<BasicResponse> getObservable1(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .updateProfile(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BasicResponse> getObserver1() {
        return new DisposableObserver<BasicResponse>() {
            @Override
            public void onNext(@NonNull BasicResponse basicResponse) {
                profileInterface.hideProgressBar();
                profileInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                profileInterface.hideProgressBar();
                e.printStackTrace();
                profileInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                profileInterface.hideProgressBar();
            }
        };
    }

}
