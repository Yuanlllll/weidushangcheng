package com.example.weidushangcheng.fragment.fragmentmvp.car.presenter;

import com.example.weidushangcheng.fragment.CarFragment;
import com.example.weidushangcheng.fragment.fragmentmvp.car.model.CarModelImp;
import com.example.weidushangcheng.fragment.fragmentmvp.car.model.ICarModel;

public class CarPresenterImp implements ICarPresenter {
    private final CarModelImp carModelImp;
    private CarFragment carFragment;

    public CarPresenterImp(CarFragment carFragment) {
        this.carFragment = carFragment;
        carModelImp = new CarModelImp();
    }

    @Override
    public void getCarPresenter(int uid,String sid) {
        carModelImp.getCarData(uid,sid,new ICarModel.CarCallBack() {
            @Override
            public void onSuccess(Object o) {
                carFragment.getCarView(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
