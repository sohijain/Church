package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BannerResponse;
import com.church.com.model.EventDetailResponse;
import com.church.com.model.EventResponse;
import com.church.com.presenter_interface.BannerPresenterInterface;
import com.church.com.presenter_interface.EventPresenterInterface;
import com.church.com.view_interface.BannerInterface;
import com.church.com.view_interface.EventViewInterface;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventPresenter implements EventPresenterInterface {

    EventViewInterface eventViewInterface;

    public EventPresenter(EventViewInterface eventViewInterface) {
        this.eventViewInterface = eventViewInterface;
    }


    public Observable<EventResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getEvent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<EventResponse> getObserver() {
        return new DisposableObserver<EventResponse>() {
            @Override
            public void onNext(@NonNull EventResponse basicResponse) {
                eventViewInterface.hideProgressBar();
                eventViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                eventViewInterface.hideProgressBar();
                e.printStackTrace();
                eventViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                eventViewInterface.hideProgressBar();
            }
        };
    }


    @Override
    public void getEvent() {
        eventViewInterface.showProgressBar();
        getObservable().subscribeWith(getObserver());;
    }

    @Override
    public void getEventDetail(Map<String, String> params) {
        eventViewInterface.showProgressBar();
        getObservable1(params).subscribeWith(getObserver1());;
    }

    public Observable<EventDetailResponse> getObservable1(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getEventDetail(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<EventDetailResponse> getObserver1() {
        return new DisposableObserver<EventDetailResponse>() {
            @Override
            public void onNext(@NonNull EventDetailResponse basicResponse) {
                eventViewInterface.hideProgressBar();
                eventViewInterface.onEventDetailSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                eventViewInterface.hideProgressBar();
                e.printStackTrace();
                eventViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                eventViewInterface.hideProgressBar();
            }
        };
    }

}
