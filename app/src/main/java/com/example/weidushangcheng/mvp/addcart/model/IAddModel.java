package com.example.weidushangcheng.mvp.addcart.model;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface IAddModel {
    void getTbData(String userId, String sessionId, RequestBody data, ModelInterface modelInterface);
    interface ModelInterface{
        void onSuccess(Object o);
        void onFailed(Throwable throwable);
    }
}
