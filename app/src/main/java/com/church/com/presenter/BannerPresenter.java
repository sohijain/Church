package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BannerResponse;
import com.church.com.model.BasicResponse;
import com.church.com.presenter_interface.BannerPresenterInterface;
import com.church.com.presenter_interface.SignInPresenterInterface;
import com.church.com.view_interface.BannerInterface;
import com.church.com.view_interface.SignInViewInterface;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BannerPresenter implements BannerPresenterInterface {

    BannerInterface bannerInterface;

    public BannerPresenter(BannerInterface bannerInterface) {
        this.bannerInterface = bannerInterface;
    }


    public Observable<BannerResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .benner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BannerResponse> getObserver() {
        return new DisposableObserver<BannerResponse>() {
            @Override
            public void onNext(@NonNull BannerResponse basicResponse) {
                bannerInterface.hideProgressBar();
                bannerInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                bannerInterface.hideProgressBar();
                e.printStackTrace();
                bannerInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                bannerInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void getBanner() {
        bannerInterface.showProgressBar();
        getObservable().subscribeWith(getObserver());;
    }
}
