package com.church.com.presenter;
import com.church.com.NetworkClient;
import com.church.com.NetworkInterface;
import com.church.com.model.BasicResponse;
import com.church.com.presenter_interface.SignUpPresenterInterface;
import com.church.com.view_interface.SignUpViewInterface;

import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignUpPresenter implements SignUpPresenterInterface {

    SignUpViewInterface signUpViewInterface;

    public SignUpPresenter(SignUpViewInterface signUpViewInterface) {
        this.signUpViewInterface = signUpViewInterface;
    }


    public Observable<BasicResponse> getObservable(Map<String, String> params) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .createUser(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BasicResponse> getObserver() {
        return new DisposableObserver<BasicResponse>() {
            @Override
            public void onNext(@NonNull BasicResponse basicResponse) {
                signUpViewInterface.hideProgressBar();
                signUpViewInterface.onSuccess(basicResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                signUpViewInterface.hideProgressBar();
                e.printStackTrace();
                signUpViewInterface.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                signUpViewInterface.hideProgressBar();
            }
        };
    }

    @Override
    public void signUp(Map<String, String> params) {
        signUpViewInterface.showProgressBar();
        getObservable(params).subscribeWith(getObserver());;
    }
}
