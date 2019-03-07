package com.example.weidushangcheng.fragment.fragmentmvp.my.circle.model;

public interface IMyCircleModel {
    //wodequanzi
    void getData(int uId, String SId, CircleModelCallBack nickModelCallBack);

    interface CircleModelCallBack {
        void onSuccess(Object o);

        void onFail(Throwable throwable);
    }
}
