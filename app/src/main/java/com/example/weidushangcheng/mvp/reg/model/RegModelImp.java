package com.example.weidushangcheng.mvp.reg.model;

import com.example.weidushangcheng.bean.RegBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RegModelImp implements IRegModel {
    @Override
    public void getRegData(String acc, String pwd, final RegModelCallBack regModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getRegbean(acc,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegBean>() {
                    @Override
                    public void onNext(RegBean value) {
                        regModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        regModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
