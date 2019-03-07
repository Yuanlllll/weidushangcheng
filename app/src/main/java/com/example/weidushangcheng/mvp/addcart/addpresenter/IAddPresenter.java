package com.example.weidushangcheng.mvp.addcart.addpresenter;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface IAddPresenter {
    void getTbPresenter(String userId, String sessionId, RequestBody data);

}
