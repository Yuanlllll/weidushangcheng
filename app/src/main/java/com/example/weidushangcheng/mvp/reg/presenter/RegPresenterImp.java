package com.example.weidushangcheng.mvp.reg.presenter;

import com.example.weidushangcheng.RegActivity;
import com.example.weidushangcheng.mvp.reg.model.IRegModel;
import com.example.weidushangcheng.mvp.reg.model.RegModelImp;

public class RegPresenterImp implements IRegPresenter {
    private RegActivity regActivity;
    private final RegModelImp regModelImp;

    public RegPresenterImp(RegActivity regActivity) {
        this.regActivity = regActivity;
        regModelImp = new RegModelImp();
    }

    @Override
    public void getRegData(String acc, String pwd) {
        regModelImp.getRegData(acc, pwd, new IRegModel.RegModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                regActivity.getRegViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
