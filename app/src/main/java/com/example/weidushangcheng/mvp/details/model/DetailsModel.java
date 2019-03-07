package com.example.weidushangcheng.mvp.details.model;

import com.example.weidushangcheng.bean.ShopDetails;
import com.example.weidushangcheng.bean.ShopDetailsBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsModel implements IDetailsModel {
    @Override
    public void getData(String commodityId,final ModelInterface modelInterface) {
        IRequest iApiService = RetrofitUtils.getInstance().creat(IRequest.class);
        iApiService.goodsDateils(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ShopDetails>() {
                    @Override
                    public void onNext(ShopDetails value) {
                        modelInterface.loadSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelInterface.loadFaild(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
