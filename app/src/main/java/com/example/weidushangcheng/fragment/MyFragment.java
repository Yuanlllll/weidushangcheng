package com.example.weidushangcheng.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weidushangcheng.MyCircleActivity;
import com.example.weidushangcheng.MyFootActivity;
import com.example.weidushangcheng.PresonActivity;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView my__text_myfootprint;
    private SimpleDraweeView my_img_head;
    private TextView my_text_myaddress;
    private TextView my_text_mycircle;
    private TextView my_text_personaldata;
    private TextView my_text_mywallet;
    private TextView my_text_name;

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        my__text_myfootprint = view.findViewById(R.id.my__text_myfootprint);
        my_img_head = view.findViewById(R.id.my_img_head);
        my_text_myaddress = view.findViewById(R.id.my_text_myaddress);
        my_text_mycircle = view.findViewById(R.id.my_text_mycircle);
        my_text_personaldata = view.findViewById(R.id.my_text_personaldata);
        my_text_mywallet = view.findViewById(R.id.my_text_mywallet);
        my_text_name = view.findViewById(R.id.my_text_name);
    }

    @Override
    protected void initData() {
        //点击事件
        my_img_head.setOnClickListener(this);
        my_text_name.setOnClickListener(this);
        my_text_personaldata.setOnClickListener(this);
        my_text_mycircle.setOnClickListener(this);
        my__text_myfootprint.setOnClickListener(this);
        my_text_mywallet.setOnClickListener(this);
        my_text_myaddress.setOnClickListener(this);

    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //头像
            case R.id.my_img_head:

                break;
                //个人中心
            case R.id.my_text_personaldata:
                startActivity(new Intent(getActivity(),PresonActivity.class));
                //接收
                SharedPreferences config = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
                String nickname = config.getString("nickname", "");
                my_text_name.setText(nickname);
                break;
                //我的圈子
            case R.id.my_text_mycircle:
                startActivity(new Intent(getActivity(),MyCircleActivity.class));
                break;
                //我的足迹
            case R.id.my__text_myfootprint:
                startActivity(new Intent(getActivity(),MyFootActivity.class));
                break;
                //我的钱包
            case R.id.my_text_mywallet:

                break;
                //我的地址
            case R.id.my_text_myaddress:

                break;
        }
    }
}
