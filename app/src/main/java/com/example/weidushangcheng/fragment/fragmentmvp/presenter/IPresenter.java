package com.example.weidushangcheng.fragment.fragmentmvp.presenter;

public interface IPresenter {
    //首页---HOME
    void getPresenterData();

    void getPresenterBannerData();

    void getSouSuoPresenterData(String keyword, int page, int count);


}
