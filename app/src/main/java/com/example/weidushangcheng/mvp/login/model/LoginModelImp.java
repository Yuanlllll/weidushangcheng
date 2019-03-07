package com.example.weidushangcheng.mvp.login.model;


import com.example.weidushangcheng.bean.LoginBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginModelImp implements ILoginModel {
    @Override
    public void getLoginData(String acc, String pwd, final LoginModelCallBack loginModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getLoginbean(acc, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean value) {
                        loginModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
