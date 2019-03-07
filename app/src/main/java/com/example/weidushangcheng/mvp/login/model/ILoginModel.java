package com.example.weidushangcheng.mvp.login.model;

public interface ILoginModel {
    void getLoginData(String acc,String pwd,LoginModelCallBack loginModelCallBack);

    public interface LoginModelCallBack{
        void onSuccess(Object o);
        void onFail(Throwable throwable);
    }
}
