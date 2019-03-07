package com.example.weidushangcheng.fragment.fragmentmvp.presenter;

import android.util.Log;

import com.example.weidushangcheng.fragment.HomeFragment;
import com.example.weidushangcheng.fragment.fragmentmvp.model.HomeModel;
import com.example.weidushangcheng.fragment.fragmentmvp.model.IHomeModel;

public class HomePresenterImp implements IPresenter {
    private HomeFragment homeFragment;
    private final HomeModel homeModel;

    public HomePresenterImp(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        homeModel = new HomeModel();
    }

    @Override
    public void getPresenterData() {
        homeModel.getModelData(new IHomeModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                homeFragment.getViewData(o);
                Log.i("aaa", "success: " + homeFragment);
            }

            @Override
            public void fail(Throwable e) {

            }
        });
    }

    @Override
    public void getPresenterBannerData() {
        homeModel.getModelBannerData(new IHomeModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                homeFragment.getViewBannerData(o);
            }

            @Override
            public void fail(Throwable e) {

            }
        });
    }

    @Override
    public void getSouSuoPresenterData(String keyword, int page, int count) {
        homeModel.getSouSuoData(keyword, page, count, new IHomeModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                homeFragment.getViewSouSuoData(o);
            }

            @Override
            public void fail(Throwable e) {

            }
        });
    }
}
