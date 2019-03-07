package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.BannerBean;
import com.example.weidushangcheng.bean.HomeBean;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private HomeBean homeBean;
    private BannerBean bannerBean;
    private final int ONE = 0;
    private final int TWO = 1;
    private final int THREE = 2;
    private final int FOUR = 3;

    public HomeAdapter(Context context, HomeBean homeBean, BannerBean bannerBean) {
        this.context = context;
        this.homeBean = homeBean;
        this.bannerBean = bannerBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner_layout, viewGroup, false);
            ViewHolderBanner viewHolderBanner = new ViewHolderBanner(view);
            return viewHolderBanner;
        } else if (i == TWO) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_layout1, viewGroup, false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        } else if (i == THREE) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_layout2, viewGroup, false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }
        return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.home_layout3, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolderBanner) {
            ((ViewHolderBanner) viewHolder).homeBanner.setData(bannerBean.getResult(), null);
            ((ViewHolderBanner) viewHolder).homeBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    BannerBean.ResultBean baner = (BannerBean.ResultBean) model;
                    Glide.with(context).load(baner.getImageUrl()).into((ImageView) view);
                    banner.setPageChangeDuration(1000);
                }
            });

        } else if (viewHolder instanceof ViewHolder1) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false);
            ((ViewHolder1) viewHolder).home_recycler01.setLayoutManager(layoutManager);
            ((ViewHolder1) viewHolder).home_text01.setText(homeBean.getResult().getRxxp().getName());
            ((ViewHolder1) viewHolder).home_recycler01.setAdapter(new Home_RecyclerAdapter01(context, homeBean));
        } else if (viewHolder instanceof ViewHolder2) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, OrientationHelper.VERTICAL, false);
            ((ViewHolder2) viewHolder).home_recycler02.setLayoutManager(layoutManager);
            Home_RecyclerAdapter02 myAdapterTwo = new Home_RecyclerAdapter02(context, homeBean);
            ((ViewHolder2) viewHolder).home_recycler02.setAdapter(myAdapterTwo);
            ((ViewHolder2) viewHolder).home_text02.setText(homeBean.getResult().getPzsh().getName());
        } else if (viewHolder instanceof ViewHolder3)

        {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder3) viewHolder).home_recycler03.setLayoutManager(gridLayoutManager);
            Home_RecyclerAdapter03 myAdapterThree = new Home_RecyclerAdapter03(context, homeBean);
            ((ViewHolder3) viewHolder).home_recycler03.setAdapter(myAdapterThree);
            ((ViewHolder3) viewHolder).home_text03.setText(homeBean.getResult().getMlss().getName());
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == ONE) {
            return ONE;
        } else if (position == TWO) {
            return TWO;
        } else if (position == THREE) {
            return THREE;
        }
        return FOUR;
    }

    class ViewHolderBanner extends RecyclerView.ViewHolder {
        @BindView(R.id.home_banner)
        XBanner homeBanner;

        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        private final RecyclerView home_recycler01;
        private final TextView home_text01;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            home_text01 = itemView.findViewById(R.id.home_text01);
            home_recycler01 = itemView.findViewById(R.id.home_recycler01);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {

        private final RecyclerView home_recycler02;
        private final TextView home_text02;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            home_text02 = itemView.findViewById(R.id.home_text02);
            home_recycler02 = itemView.findViewById(R.id.home_recycler02);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {

        private final RecyclerView home_recycler03;
        private final TextView home_text03;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            home_text03 = itemView.findViewById(R.id.home_text03);
            home_recycler03 = itemView.findViewById(R.id.home_recycler03);
        }
    }
}
