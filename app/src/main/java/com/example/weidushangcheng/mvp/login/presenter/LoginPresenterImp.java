package com.example.weidushangcheng.mvp.login.presenter;

import com.example.weidushangcheng.LoginActivity;
import com.example.weidushangcheng.mvp.login.model.ILoginModel;
import com.example.weidushangcheng.mvp.login.model.LoginModelImp;

public class LoginPresenterImp implements ILoginPresenter {
    private LoginActivity loginActivity;
    private final LoginModelImp loginModelImp;

    public LoginPresenterImp(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginModelImp = new LoginModelImp();
    }

    @Override
    public void getLoginData(String acc, String pwd) {
        loginModelImp.getLoginData(acc, pwd, new ILoginModel.LoginModelCallBack() {
            @Override
            public void onSuccess(Object o) {
                loginActivity.getLoginViewData(o);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }
}
