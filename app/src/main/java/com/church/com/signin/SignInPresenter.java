package com.church.com.signin;
import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BasicResponse;
import com.church.com.signup.SignUpPresenterInterface;
import com.church.com.signup.SignUpViewInterface;

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


    public Observable<BasicResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BasicResponse> getObserver() {
        return new DisposableObserver<BasicResponse>() {
            @Override
            public void onNext(@NonNull BasicResponse basicResponse) {
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
