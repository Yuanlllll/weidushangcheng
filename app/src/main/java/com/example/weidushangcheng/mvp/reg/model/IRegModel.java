package com.example.weidushangcheng.mvp.reg.model;

public interface IRegModel {
    void getRegData(String acc,String pwd,RegModelCallBack regModelCallBack);

    public interface RegModelCallBack{
        void onSuccess(Object o);
        void onFail(Throwable throwable);
    }
}
