package com.example.weidushangcheng.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.adapter.CircleAdapter;
import com.example.weidushangcheng.base.BaseActivity;
import com.example.weidushangcheng.base.BaseFragment;
import com.example.weidushangcheng.bean.CircleBean;
import com.example.weidushangcheng.fragment.fragmentmvp.circle.presenter.PresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.circle.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleFragment extends BaseFragment implements IView {
    @BindView(R.id.circle_xrecycler)
    XRecyclerView circleXrecycler;
    Unbinder unbinder;
    private PresenterImp presenterImp;
    private int page = 1;
    int count = 5;
    private CircleAdapter circleAdapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initData() {
        presenterImp = new PresenterImp(this);
        presenterImp.getPresenterCircleData(page, count);
        //多条目xrecyclerview
        getXRecyclerVIew();
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void getXRecyclerVIew() {
        circleXrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                presenterImp.getPresenterCircleData(1, count);
                circleAdapter.notifyDataSetChanged();
                circleXrecycler.refreshComplete();
            }

            //加载
            @Override
            public void onLoadMore() {
                presenterImp.getPresenterCircleData(page++, count);
                circleAdapter.notifyDataSetChanged();
                circleXrecycler.loadMoreComplete();
            }
        });
    }

    @Override
    public void getViewCircleData(Object o) {
        if (o != null) {
            CircleBean circleBean = (CircleBean) o;
            Log.i("aaa", "getViewCircleData: " + circleBean.getMessage());
            //创建线性布局
            circleXrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
            //设置条目之间的的间距
            //circleXrecycler.addItemDecoration(new SpaceItemDecoration(30));
            //创建适配器
            circleAdapter = new CircleAdapter(getActivity(), circleBean);
            circleXrecycler.setAdapter(circleAdapter);
        }
    }
}
