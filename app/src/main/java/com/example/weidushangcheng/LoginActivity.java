package com.example.weidushangcheng;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidushangcheng.bean.LoginBean;
import com.example.weidushangcheng.mvp.login.presenter.LoginPresenterImp;
import com.example.weidushangcheng.mvp.login.view.ILoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.login_edit_acc)
    EditText loginEditAcc;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_text_reg)
    TextView loginTextReg;
    @BindView(R.id.login_checkbox_remember)
    CheckBox loginCheckboxRemember;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_img_eye)
    CheckBox loginImgEye;
    private LoginPresenterImp loginPresenterImp;
    private String acc;
    private String pwd;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenterImp = new LoginPresenterImp(this);
        //记住密码+显示隐藏密码
        rememberPassword();
        // 注册订阅者
        EventBus.getDefault().register(this);
    }



    //EventBus传值---注册之后传值
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(RegActivity.MessageEvent event) {
        //Log.i("event", "message is " + event.getMessage());
        // 更新界面
        // Toast.makeText(LoginActivity.this, event.getMessage(), Toast.LENGTH_SHORT).show();
        phone = event.getMessage().substring(0, 11).trim();
        password = event.getMessage().substring(11).trim();
        //loginEditAcc.setText();
        // 移除粘性事件
        setData();
        EventBus.getDefault().removeStickyEvent(event);
    }
    private void setData() {
        loginEditAcc.setText(phone);
        Toast.makeText(LoginActivity.this, phone, Toast.LENGTH_SHORT).show();
        loginEditPwd.setText(password);
    }

    private void rememberPassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag", false);
        loginCheckboxRemember.setChecked(flag);
        if (flag) {
            String phone = sharedPreferences.getString("phone", "");
            String pwd1 = sharedPreferences.getString("pwd", "");
            loginEditAcc.setText(phone);
            loginEditPwd.setText(pwd1);
        }
        loginImgEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    loginEditPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    loginEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void getLoginViewData(Object viewData) {
        if (viewData != null) {
            LoginBean loginBean = (LoginBean) viewData;
            //传值--登录的id
            SharedPreferences sharedPreference = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreference.edit();
            String status = loginBean.getStatus();
            if (status.equals("0000")) {
                Log.i("123", "showMsg: "+loginBean.getResult().getUserId());
                editor.putString("userId",loginBean.getResult().getUserId()+"");
                editor.putString("sessionId",loginBean.getResult().getSessionId()+"");
                editor.commit();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if (loginBean.getMessage().equals("登录成功")) {
                //记住密码---传值修改
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (loginCheckboxRemember.isChecked()) {
                    edit.putBoolean("flag", true);
                    edit.putString("phone", acc);
                    edit.putString("pwd", pwd);
                } else {
                    edit.putBoolean("flag", false);
                }
                edit.commit();
                Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else if (loginBean.getMessage().equals("登陆失败,手机号或密码错误")) {
                Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //点击事件

    @OnClick({R.id.login_text_reg, R.id.login_btn_login, R.id.login_img_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 快速注册
            case R.id.login_text_reg:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                break;
            // 点击登录,判非空
            case R.id.login_btn_login:
                acc = loginEditAcc.getText().toString();
                pwd = loginEditPwd.getText().toString();
                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "账号或手机号不能为空啦", Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenterImp.getLoginData(acc, pwd);
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }
}

