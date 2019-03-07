package com.example.weidushangcheng;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.example.weidushangcheng.adapter.FootAdapter;
import com.example.weidushangcheng.base.BaseActivity;
import com.example.weidushangcheng.bean.MyFootBean;
import com.example.weidushangcheng.fragment.fragmentmvp.my.foot.presenter.FootPresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.my.foot.view.IFootview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFootActivity extends BaseActivity implements IFootview {

    @BindView(R.id.foot_xrecy)
    XRecyclerView footXrecy;
    private FootPresenterImp footPresenterImp;
    private int page;
    private FootAdapter footAdapter;
    private boolean foot_flag;
    private final int COUNT_ITEM = 2;
    private String sessionId;
    private int d;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_foot;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        footPresenterImp = new FootPresenterImp(this);
        page = 1;
        //布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COUNT_ITEM);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        footXrecy.setLayoutManager(gridLayoutManager);
        //允许刷新和加载
        footXrecy.setLoadingMoreEnabled(true);
        footXrecy.setPullRefreshEnabled(true);
        footAdapter = new FootAdapter(this);
        footXrecy.setAdapter(footAdapter);
        footXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initUrl(page);
            }

            @Override
            public void onLoadMore() {
                if (foot_flag) {
                    footXrecy.loadMoreComplete();
                } else {
                    footXrecy.setLoadingMoreEnabled(true);
                }
                initUrl(page);
            }
        });
        initUrl(page);
    }

    private void initUrl(int page) {
        SharedPreferences preference = getSharedPreferences("config", Context.MODE_PRIVATE);
        sessionId = preference.getString("sessionId", "");
        String userId = preference.getString("userId", "");
        d = Integer.parseInt(userId);
        footPresenterImp.getModel(d, sessionId);
    }

    @Override
    public void getViewData(Object o) {
        if (o instanceof MyFootBean) {
            MyFootBean MyFootBean = (MyFootBean) o;
            if (page == 1) {
                footAdapter.setList(MyFootBean.getResult());
            } else {
                footAdapter.addList(MyFootBean.getResult());
            }
            //停止刷新加载
            footXrecy.refreshComplete();
            footXrecy.loadMoreComplete();
            page++;
            if (MyFootBean.getResult().size() == 0) {
                foot_flag = true;
            } else {
                foot_flag = false;
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
