package com.example.weidushangcheng;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidushangcheng.base.BaseActivity;
import com.example.weidushangcheng.bean.MyUpdateNameBean;
import com.example.weidushangcheng.bean.MyUpdatePwdBean;
import com.example.weidushangcheng.bean.SelectSelfBean;
import com.example.weidushangcheng.fragment.fragmentmvp.my.nick.peesenter.NickPresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.my.nick.view.INickView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresonActivity extends BaseActivity implements INickView {

    @BindView(R.id.data_image_header)
    SimpleDraweeView dataImageHeader;
    @BindView(R.id.data_text_name)
    TextView dataTextName;
    @BindView(R.id.data_text_pass)
    TextView dataTextPass;
    private NickPresenterImp nickPresenterImp;
    private String nick;
    private int uid;
    private String sessionId;
    private EditText alertEditName;

    @Override
    protected int initLayout() {
        return R.layout.activity_preson;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //互绑
        nickPresenterImp = new NickPresenterImp(this);
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");
        sessionId = sharedPreferences.getString("sessionId", "");
        uid = Integer.parseInt(userId);
        //获取用户信息
        nickPresenterImp.getSelfModel(uid, sessionId);
    }

    @Override
    public void getNickViewData(Object o) {
        if (o instanceof SelectSelfBean) {
            SelectSelfBean dataBean = (SelectSelfBean) o;
            //查询成功,赋值
            if (dataBean.getStatus().equals("0000")) {
                dataImageHeader.setImageURI(Uri.parse(dataBean.getResult().getHeadPic()));
                dataTextName.setText(dataBean.getResult().getNickName());
                dataTextPass.setText(dataBean.getResult().getPassword() + "");
            }
        }
        if (o instanceof MyUpdateNameBean) {
            MyUpdateNameBean nameBean = (MyUpdateNameBean) o;
            if (nameBean.getStatus().equals("0000")) {
                Toast.makeText(this, nameBean.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, nameBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (o instanceof MyUpdatePwdBean) {
            MyUpdatePwdBean passBean = (MyUpdatePwdBean) o;
            if (passBean.getStatus().equals("0000")) {
                Toast.makeText(this, passBean.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, passBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.data_image_header, R.id.data_text_name, R.id.data_text_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_image_header:
                break;
            case R.id.data_text_name:
                //弹出对话框,修改昵称
                initAlert();
                break;
            case R.id.data_text_pass:
                //弹出对话框,修改密码
                initPass();
                break;
        }
    }

    private void initAlert() {
        View nameView = View.inflate(PresonActivity.this, R.layout.alert_name, null);
        alertEditName = nameView.findViewById(R.id.alert_edit_name);
        final AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setView(nameView);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);
        //确定事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String params = alertEditName.getText().toString();
                dataTextName.setText(params+"");
                nickPresenterImp.getModel(uid, sessionId, params);
                dialog.dismiss();
                //传值
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("nickname",params);
                edit.commit();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void initPass() {
        View passView=View.inflate(PresonActivity.this,R.layout.alert_pass,null);
        final EditText edit_pass = passView.findViewById(R.id.alert_edit_pass);
        final EditText edit_surepass = passView.findViewById(R.id.alert_edit_surepass);
        final AlertDialog.Builder builder=new AlertDialog
                .Builder(this)
                .setView(passView);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);
        //确定事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldpwd = edit_pass.getText().toString();
                String newPwd = edit_surepass.getText().toString();
                nickPresenterImp.getPwdModel(uid,sessionId,oldpwd,newPwd);
                dialog.dismiss();
                //传值
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("oldpwd",oldpwd);
                edit.putString("newPwd",newPwd);
                edit.commit();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initTitle() {

    }
}
