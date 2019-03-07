package com.example.weidushangcheng.fragment.fragmentmvp.my.nick.peesenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public interface INickPresenter {
    //查询永华信息
    void getSelfModel(int uId, String SId);

    //修改昵称
    void getModel(int uId, String SId, String nickName);

    //修改密码
    void getPwdModel(int uId, String SId, String oldpwd, String newpwd);

}
