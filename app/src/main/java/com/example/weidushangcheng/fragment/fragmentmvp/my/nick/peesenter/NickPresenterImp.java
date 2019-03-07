package com.example.weidushangcheng.fragment.fragmentmvp.my.nick.peesenter;

import com.example.weidushangcheng.PresonActivity;
import com.example.weidushangcheng.fragment.fragmentmvp.my.nick.model.INickModel;
import com.example.weidushangcheng.fragment.fragmentmvp.my.nick.model.NickModelImp;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class NickPresenterImp implements INickPresenter {
    private final NickModelImp nickModelImp;
    private PresonActivity presonActivity;

    public NickPresenterImp(PresonActivity presonActivity) {
        this.presonActivity = presonActivity;
        nickModelImp = new NickModelImp();
    }

    @Override
    public void getSelfModel(int uId, String SId) {

    }

    @Override
    public void getModel(int uId, String SId, String nickName) {
        nickModelImp.getModel(uId, SId, nickName, new INickModel.NickModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                presonActivity.getNickViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    @Override
    public void getPwdModel(int uId, String SId, String oldpwd, String newpwd) {
        nickModelImp.getPwdModel(uId, SId, oldpwd,newpwd, new INickModel.NickModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                presonActivity.getNickViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
