package com.example.weidushangcheng.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.weidushangcheng.network.NetWorksUtils;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView(savedInstanceState);
        initData();
        initTitle();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initTitle();

    protected abstract int initLayout();

    protected abstract void initView(Bundle savedInstanceState);


    protected abstract void initData();
}