package com.church.com.presenter;

import androidx.annotation.NonNull;

import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BannerResponse;
import com.church.com.model.CategoryResponse;
import com.church.com.presenter_interface.BannerPresenterInterface;
import com.church.com.presenter_interface.CategoryPresenterInterface;
import com.church.com.view_interface.BannerInterface;
import com.church.com.view_interface.CategoryViewInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryPresenterInterface {

    CategoryViewInterface categoryViewInterface;

    public CategoryPresenter(CategoryViewInterface categoryViewInterface) {
        this.categoryViewInterface = categoryViewInterface;
    }


    public Observable<CategoryResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<CategoryResponse> getObserver() {
        return new DisposableObserver<CategoryResponse>() {
            @Override
            public void onNext(@NonNull CategoryResponse basicResponse) {
                categoryViewInterface.hideProgressBar();
                categoryViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                categoryViewInterface.hideProgressBar();
                e.printStackTrace();
                categoryViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                categoryViewInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void getCategory() {
        categoryViewInterface.showProgressBar();
        getObservable().subscribeWith(getObserver());;
    }
}
