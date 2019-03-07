package com.example.weidushangcheng.fragment.fragmentmvp.circle.model;

import android.util.Log;

import com.example.weidushangcheng.bean.CircleBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ModelImp implements IModel {
    @Override
    public void getModelCircle(int page, int count,final ModelCallBack modelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getCirclebean(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CircleBean>() {
                    @Override
                    public void onNext(CircleBean value) {
                        modelCallBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelCallBack.fail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //Log.i("jxm", "getModelCircle: "+iRequest.getCirclebean(page));
    }
}
