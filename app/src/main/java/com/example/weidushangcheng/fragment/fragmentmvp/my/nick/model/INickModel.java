package com.example.weidushangcheng.fragment.fragmentmvp.my.nick.model;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public interface INickModel {
    //查询用户信息
    void getSelfModel(int uId, String SId, NickModelCallBack nickModelCallBack);

    //修改昵称
    void getModel(int uId, String SId, String nickName, NickModelCallBack nickModelCallBack);

    //修改昵称
    void getPwdModel(int uId, String SId, String oldpwd, String newpwd, NickModelCallBack nickModelCallBack);

    interface NickModelCallBack {
        void onSuccess(Object o);

        void onFail(Throwable throwable);
    }
}
