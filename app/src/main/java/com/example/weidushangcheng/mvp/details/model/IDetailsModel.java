package com.example.weidushangcheng.mvp.details.model;

import java.util.Map;

public interface IDetailsModel {
    void getData(String commodityId, ModelInterface modelInterface);
    interface ModelInterface{
        void loadSuccess(Object o);
        void loadFaild(Throwable throwable);
    }
}
