package com.example.weidushangcheng.fragment.fragmentmvp.car.model;

public interface ICarModel {
    void getCarData(int uid,String sid,CarCallBack carCallBack);

    public interface CarCallBack{
        void onSuccess(Object o);
        void onFail(Throwable throwable);
    }
}
