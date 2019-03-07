package com.example.weidushangcheng.fragment.fragmentmvp.car.model;

import com.example.weidushangcheng.bean.CarBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CarModelImp implements ICarModel {
    @Override
    public void getCarData(int uid,String sid,final CarCallBack carCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getCarBean(uid,sid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CarBean>() {
                    @Override
                    public void onNext(CarBean value) {
                        carCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        carCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
