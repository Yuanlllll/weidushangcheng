package com.example.weidushangcheng.fragment.fragmentmvp.my.circle.presenter;

import com.example.weidushangcheng.MyCircleActivity;
import com.example.weidushangcheng.fragment.fragmentmvp.my.circle.model.IMyCircleModel;
import com.example.weidushangcheng.fragment.fragmentmvp.my.circle.model.MyCircleModelImp;

public class IMyCirclePresenterImp implements IMyCirclePresenter {
    private final MyCircleModelImp myCircleModelImp;
    private MyCircleActivity myCircleActivity;

    public IMyCirclePresenterImp(MyCircleActivity myCircleActivity) {
        this.myCircleActivity = myCircleActivity;
        myCircleModelImp = new MyCircleModelImp();
    }

    @Override
    public void getModel(int uId, String SId) {
        myCircleModelImp.getData(uId, SId, new IMyCircleModel.CircleModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                myCircleActivity.getCircleViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
