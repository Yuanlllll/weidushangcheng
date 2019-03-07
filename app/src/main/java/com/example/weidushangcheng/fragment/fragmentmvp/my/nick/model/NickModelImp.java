package com.example.weidushangcheng.fragment.fragmentmvp.my.nick.model;

import android.util.Log;

import com.example.weidushangcheng.MainActivity;
import com.example.weidushangcheng.bean.MyUpdateNameBean;
import com.example.weidushangcheng.bean.MyUpdatePwdBean;
import com.example.weidushangcheng.bean.SelectSelfBean;
import com.example.weidushangcheng.network.IRequest;
import com.example.weidushangcheng.network.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class NickModelImp implements INickModel {
    @Override
    public void getSelfModel(int uId, String SId, final NickModelCallBack nickModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getSelf(uId, SId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SelectSelfBean>() {
                    @Override
                    public void onNext(SelectSelfBean value) {
                        nickModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        nickModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getModel(int uId, String SId, String nickName, final NickModelCallBack nickModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getMyUpdateName(uId, SId, nickName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyUpdateNameBean>() {
                    @Override
                    public void onNext(MyUpdateNameBean value) {
                        nickModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        nickModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getPwdModel(int uId, String SId, String oldpwd, String newpwd, final NickModelCallBack nickModelCallBack) {
        IRequest iRequest = RetrofitUtils.getInstance().creat(IRequest.class);
        iRequest.getMyUpdatePwd(uId, SId, oldpwd, newpwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyUpdatePwdBean>() {
                    @Override
                    public void onNext(MyUpdatePwdBean value) {
                        nickModelCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        nickModelCallBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
