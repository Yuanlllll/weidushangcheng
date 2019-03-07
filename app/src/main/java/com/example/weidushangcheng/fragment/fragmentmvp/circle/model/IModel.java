package com.example.weidushangcheng.fragment.fragmentmvp.circle.model;

public interface IModel {
    //圈子---circle
    void getModelCircle(int page,int count, ModelCallBack modelCallBack);

    public interface ModelCallBack {
        void success(Object o);

        void fail(Throwable e);
    }
}
