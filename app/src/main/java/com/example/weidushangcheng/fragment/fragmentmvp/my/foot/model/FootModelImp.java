package com.example.weidushangcheng.fragment.fragmentmvp.my.foot.model;

import com.example.weidushangcheng.bean.MyFootBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FootModelImp implements IFootModel {
    @Override
    public void getData(int uId, String SId, final FootModelCallBack nickModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getMyFoot(uId, SId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyFootBean>() {
                    @Override
                    public void onNext(MyFootBean value) {
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
