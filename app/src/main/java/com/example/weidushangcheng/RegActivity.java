package com.example.weidushangcheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidushangcheng.bean.RegBean;
import com.example.weidushangcheng.mvp.reg.presenter.RegPresenterImp;
import com.example.weidushangcheng.mvp.reg.view.IRegview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements IRegview {

    @BindView(R.id.reg_edit_acc)
    EditText regEditAcc;
    @BindView(R.id.reg_edit_verify)
    EditText regEditVerify;
    @BindView(R.id.reg_edit_pwd)
    EditText regEditPwd;
    @BindView(R.id.reg_text_getverify)
    TextView regTextGetverify;
    @BindView(R.id.reg_text_login)
    TextView regTextLogin;
    @BindView(R.id.reg_btn_reg)
    Button regBtnReg;
    private RegPresenterImp regPresenterImp;
    private String acc;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regPresenterImp = new RegPresenterImp(this);
    }

    @Override
    public void getRegViewData(Object viewData) {
        if (viewData != null) {
            RegBean regBean = (RegBean) viewData;
            if (regBean.getMessage().equals("注册成功")) {
                Toast.makeText(RegActivity.this,regBean.getMessage(),Toast.LENGTH_SHORT).show();
                /*
                *返回登录界面同时EventBus传值
                */
                // 发布粘性事件
                EventBus.getDefault().postSticky(new MessageEvent(acc,pwd));
                //跳转
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
            } else if (regBean.getMessage().equals("该手机号已注册，不能重复注册")){
                Toast.makeText(RegActivity.this,regBean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    @OnClick({R.id.reg_text_getverify, R.id.reg_text_login, R.id.reg_btn_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.reg_text_getverify:
                break;
            //已有账号,快速登录
            case R.id.reg_text_login:
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
                break;
            //非空
            case R.id.reg_btn_reg:
                acc = regEditAcc.getText().toString();
                pwd = regEditPwd.getText().toString();
                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegActivity.this, "账号或手机号不能为空啦", Toast.LENGTH_SHORT).show();
                } else {
                    regPresenterImp.getRegData(acc, pwd);
                }
                break;
        }
    }

    //自定义一个事件类
    public class MessageEvent{
        private String phone;
        private String passwod;
        public  MessageEvent(String phone,String passwod){
            this.phone=phone;
            this.passwod=passwod;
        }
        public String getMessage() {
            return phone+","+passwod;
        }

        public void setMessage(String phone,String passwod) {
            this.phone = phone;
            this.passwod = passwod;
        }
    }
}
