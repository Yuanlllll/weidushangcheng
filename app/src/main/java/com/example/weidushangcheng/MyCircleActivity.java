package com.example.weidushangcheng;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.ImageView;

import com.example.weidushangcheng.adapter.SelectCircleAdapter;
import com.example.weidushangcheng.base.BaseActivity;
import com.example.weidushangcheng.bean.MyCircleBean;
import com.example.weidushangcheng.fragment.fragmentmvp.my.circle.presenter.IMyCirclePresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.my.circle.view.IMyCircleView;
import com.example.weidushangcheng.fragment.fragmentmvp.my.presenter.MyPresenterImp;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCircleActivity extends BaseActivity implements IMyCircleView {

    @BindView(R.id.cir_image_delete)
    ImageView cirImageDelete;
    @BindView(R.id.cir_xrecy)
    XRecyclerView cirXrecy;
    private IMyCirclePresenterImp iMyCirclePresenterImp;
    private int page;
    private  boolean circle_flag;
    private SelectCircleAdapter selectCircleAdapter;
    private String sessionId;
    private int d;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_circle;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        iMyCirclePresenterImp = new IMyCirclePresenterImp(this);
        //初始化Xrecyclerview
        initXRecy();
    }

    private void initXRecy() {
        page = 1;
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        cirXrecy.setLayoutManager(linearLayoutManager);
        //允许刷新和加载
        cirXrecy.setLoadingMoreEnabled(true);
        cirXrecy.setPullRefreshEnabled(true);
        selectCircleAdapter = new SelectCircleAdapter(this);
        cirXrecy.setAdapter(selectCircleAdapter);

        cirXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initSelectUrl(page);
            }

            @Override
            public void onLoadMore() {
                if (circle_flag) {
                    cirXrecy.loadMoreComplete();
                } else {
                    cirXrecy.setLoadingMoreEnabled(true);
                }
                initSelectUrl(page);
            }
        });
        initSelectUrl(page);
    }

    //查询圈子
    private void initSelectUrl(int page) {
        SharedPreferences preference = getSharedPreferences("config", Context.MODE_PRIVATE);
        sessionId = preference.getString("sessionId", "");
        String userId = preference.getString("userId", "");
        d = Integer.parseInt(userId);
        iMyCirclePresenterImp.getModel(d,sessionId);
    }

    @Override
    public void getCircleViewData(Object o) {
        if (o instanceof MyCircleBean){
            MyCircleBean selectCircleBean= (MyCircleBean) o;
            if (page==1){
                selectCircleAdapter.setList(selectCircleBean.getResult());
            }else{
                selectCircleAdapter.addList(selectCircleBean.getResult());
            }
            //停止刷新加载
            cirXrecy.refreshComplete();
            cirXrecy.loadMoreComplete();
            page++;
            if (selectCircleBean.getResult().size()==0){
                circle_flag=true;
            }else{
                circle_flag=false;
            }
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initTitle() {

    }
}
