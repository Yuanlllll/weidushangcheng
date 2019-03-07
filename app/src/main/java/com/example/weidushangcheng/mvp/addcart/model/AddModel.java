package com.example.weidushangcheng.mvp.addcart.model;

import android.util.Log;

import com.example.weidushangcheng.bean.TongBuCartBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class AddModel implements IAddModel {


    @Override
    public void getTbData(String userId, String sessionId, RequestBody data, final ModelInterface modelInterface) {
        IRequest iApiService = RetrofitUtils.getInstance().creat(IRequest.class);
        iApiService.tbCar(userId, sessionId, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<TongBuCartBean>() {

                    @Override
                    public void onNext(TongBuCartBean value) {
                        modelInterface.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelInterface.onFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
