package com.example.weidushangcheng.fragment.fragmentmvp.circle.presenter;

import android.util.Log;

import com.example.weidushangcheng.fragment.CircleFragment;
import com.example.weidushangcheng.fragment.fragmentmvp.circle.model.IModel;
import com.example.weidushangcheng.fragment.fragmentmvp.circle.model.ModelImp;

public class PresenterImp implements IPresenter {
    private final ModelImp modelImp;
    private CircleFragment circleFragment;

    public PresenterImp(CircleFragment circleFragment) {
        this.circleFragment = circleFragment;
        modelImp = new ModelImp();
    }

    @Override
    public void getPresenterCircleData(int page,int count) {
        modelImp.getModelCircle(page,count, new IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                circleFragment.getViewCircleData(o);
                //Log.i("jxm", "success: "+o);
            }

            @Override
            public void fail(Throwable e) {

            }
        });
    }
}
