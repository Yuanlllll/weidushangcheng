package com.example.weidushangcheng.fragment.fragmentmvp.my.circle.model;

import com.example.weidushangcheng.bean.MyCircleBean;
import com.example.weidushangcheng.bean.MyUpdatePwdBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyCircleModelImp implements IMyCircleModel {
    @Override
    public void getData(int uId, String SId, final CircleModelCallBack nickModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getMyCircle(uId, SId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyCircleBean>() {
                    @Override
                    public void onNext(MyCircleBean value) {
                        nickModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        nickModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
