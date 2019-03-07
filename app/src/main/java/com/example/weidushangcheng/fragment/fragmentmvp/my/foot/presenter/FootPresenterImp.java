package com.example.weidushangcheng.fragment.fragmentmvp.my.foot.presenter;

import com.example.weidushangcheng.MyFootActivity;
import com.example.weidushangcheng.fragment.fragmentmvp.my.foot.model.FootModelImp;
import com.example.weidushangcheng.fragment.fragmentmvp.my.foot.model.IFootModel;
import com.example.weidushangcheng.fragment.fragmentmvp.my.foot.presenter.IFootPresenter;

public class FootPresenterImp implements IFootPresenter {
    private final FootModelImp footModelImp;
    private MyFootActivity myFootActivity;

    public FootPresenterImp(MyFootActivity myFootActivity) {
        this.myFootActivity = myFootActivity;
        footModelImp = new FootModelImp();
    }

    @Override
    public void getModel(int uId, String SId) {
        footModelImp.getData(uId, SId, new IFootModel.FootModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                myFootActivity.getViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
