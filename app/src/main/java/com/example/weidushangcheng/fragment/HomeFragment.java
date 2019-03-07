package com.example.weidushangcheng.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.adapter.HomeAdapter;
import com.example.weidushangcheng.adapter.MySouAdapter;
import com.example.weidushangcheng.bean.BannerBean;
import com.example.weidushangcheng.bean.HomeBean;
import com.example.weidushangcheng.bean.SouSuoBean;
import com.example.weidushangcheng.fragment.fragmentmvp.presenter.HomePresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.home_img_sou)
    ImageView homeImgSou;
    @BindView(R.id.home_text_sou)
    TextView homeTextSou;
    @BindView(R.id.linear_layout_home)
    LinearLayout linearLayoutHome;
    @BindView(R.id.sou_img)
    ImageView souImg;
    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.sou_text)
    TextView souText;
    @BindView(R.id.error_linear)
    LinearLayout errorLinear;
    @BindView(R.id.gou_recycler_view)
    RecyclerView gouRecyclerView;
    @BindView(R.id.linear_layout_sou)
    LinearLayout linearLayoutSou;
    private HomePresenterImp homePresenterImp;
    private String keyword = "abc";
    private int page = 1;
    private int count = 10;
    private HomeBean homeBean;
    private BannerBean bannerBean;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        homePresenterImp = new HomePresenterImp(this);
        homePresenterImp.getPresenterData();
        homePresenterImp.getPresenterBannerData();
        homePresenterImp.getSouSuoPresenterData(keyword, page, count);
        return view;
    }

    @Override
    public void getViewData(Object viewData) {
        if (viewData != null) {
            homeBean = (HomeBean) viewData;
            //Log.i("aaa", "getViewData: "+homeBean.getMessage());
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
            recyclerview.setAdapter(new HomeAdapter(getActivity(), homeBean, bannerBean));
        }
    }

    @Override
    public void getViewBannerData(Object viewData) {
        if (viewData != null) {
            /*
             *banner在适配器中获取数据
             */
            bannerBean = (BannerBean) viewData;
            //Log.i("getViewBannerData", "getViewBannerData: " + bannerBean.getMessage());
            recyclerview.setAdapter(new HomeAdapter(getActivity(), homeBean, bannerBean));
        }
    }

    @Override
    public void getViewSouSuoData(Object viewData) {
        homeTextSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenterImp.getSouSuoPresenterData(keyword, page, count);
                linearLayoutHome.setVisibility(View.GONE);
                linearLayoutSou.setVisibility(View.VISIBLE);
            }
        });


        souImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutHome.setVisibility(View.VISIBLE);
                linearLayoutSou.setVisibility(View.GONE);
                editSou.setText("");
            }
        });

        souText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editSou.getText().toString();
                //Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                if (string.length() > 0) {
                    homePresenterImp.getSouSuoPresenterData(string, page, count);
                } else {
                    homePresenterImp.getSouSuoPresenterData(keyword, page, count);

                }
            }
        });
        if (viewData != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            gouRecyclerView.setLayoutManager(gridLayoutManager);
            // System.out.println(o);
            SouSuoBean souSuoBean = (SouSuoBean) viewData;
            List<SouSuoBean.ResultBean> suoBeanResult = souSuoBean.getResult();
            if (suoBeanResult.size() > 0) {
                MySouAdapter mySouAdapter = new MySouAdapter(getActivity(), suoBeanResult);
                gouRecyclerView.setAdapter(mySouAdapter);
                gouRecyclerView.setVisibility(View.VISIBLE);
                errorLinear.setVisibility(View.GONE);
            } else {
                gouRecyclerView.setVisibility(View.GONE);
                errorLinear.setVisibility(View.VISIBLE);
            }


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
