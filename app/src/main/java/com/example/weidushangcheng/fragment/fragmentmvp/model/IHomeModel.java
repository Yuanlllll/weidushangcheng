package com.example.weidushangcheng.fragment.fragmentmvp.model;

public interface IHomeModel {
    //首页---HOME
    void getModelData(ModelCallBack modelCallBack);

    //banner
    void getModelBannerData(ModelCallBack modelCallBack);

    //搜索
    void getSouSuoData(String keyword, int page, int count, ModelCallBack modelInterface);

    public interface ModelCallBack {
        void success(Object o);

        void fail(Throwable e);
    }
}
