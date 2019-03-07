package com.example.weidushangcheng.fragment.fragmentmvp.model;

import com.example.weidushangcheng.bean.BannerBean;
import com.example.weidushangcheng.bean.HomeBean;
import com.example.weidushangcheng.bean.SouSuoBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements IHomeModel {
    @Override
    public void getModelData(final ModelCallBack modelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getHomebean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean value) {
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
    }

    @Override
    public void getModelBannerData(final ModelCallBack modelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getHomeBannerbean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean value) {
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
    }

    @Override
    public void getSouSuoData(String keyword, int page, int count, final ModelCallBack modelInterface) {
        IRequest iApiService = RetrofitUtils.getInstance().creat(IRequest.class);
        iApiService.souGoods(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SouSuoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SouSuoBean value) {
                        modelInterface.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelInterface.fail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

