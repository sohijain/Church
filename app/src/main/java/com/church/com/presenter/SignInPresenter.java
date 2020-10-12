package com.church.com.presenter;
import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BasicResponse;
import com.church.com.model.LoginResponse;
import com.church.com.presenter_interface.SignInPresenterInterface;
import com.church.com.view_interface.SignInViewInterface;

import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignInPresenter implements SignInPresenterInterface {

    SignInViewInterface signInViewInterface;

    public SignInPresenter(SignInViewInterface signInViewInterface) {
        this.signInViewInterface = signInViewInterface;
    }


    public Observable<LoginResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<LoginResponse> getObserver() {
        return new DisposableObserver<LoginResponse>() {
            @Override
            public void onNext(@NonNull LoginResponse basicResponse) {
                signInViewInterface.hideProgressBar();
                signInViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                signInViewInterface.hideProgressBar();
                e.printStackTrace();
                signInViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                signInViewInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void signIn(Map<String, String> params) {
        signInViewInterface.showProgressBar();
        getObservable(params).subscribeWith(getObserver());;
    }
}
