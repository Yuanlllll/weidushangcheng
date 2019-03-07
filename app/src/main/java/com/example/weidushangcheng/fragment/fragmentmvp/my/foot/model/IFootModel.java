package com.example.weidushangcheng.fragment.fragmentmvp.my.foot.model;

public interface IFootModel {
    //我的足迹
    void getData(int uId, String SId, FootModelCallBack nickModelCallBack);

    interface FootModelCallBack {
        void onSuccess(Object o);

        void onFail(Throwable throwable);
    }
}
