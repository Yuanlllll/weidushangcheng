package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhuang.likeviewlibrary.LikeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private CircleBean circleBean;
    public static final int TYPE_ONE_IMAGE = 0;
    public static final int TYPE_TWO_IMAGE = 1;

    public CircleAdapter(Context context, CircleBean circleBean) {
        this.context = context;
        this.circleBean = circleBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE_IMAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.circle_layout1, parent, false);
            return new ViewHolder(view);
        } else {
            return new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.circle_layout2, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).circleTextName1.setText(circleBean.getResult().get(position).getNickName());
            //Glid圆形图片
            ((ViewHolder) holder).circleIcon1.setImageURI(Uri.parse(circleBean.getResult().get(position).getHeadPic()));
            //RequestOptions requestOptions = RequestOptions.circleCropTransform();
            //Glide.with(context).load(circleBean.getResult().get(position).getHeadPic()).apply(requestOptions).into(((ViewHolder) holder).circleIcon1);
            //long类型转化成data类型
            Long dateLong = Long.valueOf(circleBean.getResult().get(position).getCreateTime());
            String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
            ((ViewHolder) holder).circleTextTime1.setText(time);
            ((ViewHolder) holder).circleTextTitle1.setText(circleBean.getResult().get(position).getContent());
            ((ViewHolder) holder).circleLikeview.setLikeCount(Integer.parseInt(circleBean.getResult().get(position).getGreatNum()));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder) holder).circleRecycler1.setLayoutManager(linearLayoutManager);
            Circle_RecyclerAdapter oneItmeAdapter = new Circle_RecyclerAdapter(context, circleBean);
            ((ViewHolder) holder).circleRecycler1.setAdapter(oneItmeAdapter);
        }
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).circleTextName2.setText(circleBean.getResult().get(position).getNickName());

            //RequestOptions requestOptions = RequestOptions.circleCropTransform();
            //Glide.with(context).load(circleBean.getResult().get(position).getHeadPic()).apply(requestOptions).into(((ItemTwoViewHolder) viewHolder).circleTwoImg);
            Long dateLong = Long.valueOf(circleBean.getResult().get(position).getCreateTime());
            String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
            ((ViewHolder1) holder).circleTextTime2.setText(time);
            ((ViewHolder1) holder).circleTextTitle2.setText(circleBean.getResult().get(position).getContent());
            ((ViewHolder1) holder).circleLikeview2.setLikeCount(Integer.parseInt(circleBean.getResult().get(position).getGreatNum()));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            ((ViewHolder1) holder).circleRecycler2.setLayoutManager(linearLayoutManager);
            Circle_RecyclerAdapter2 twoItmeAdapter = new Circle_RecyclerAdapter2(context, circleBean);
            ((ViewHolder1) holder).circleRecycler2.setAdapter(twoItmeAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return circleBean.getResult().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_icon1)
        SimpleDraweeView circleIcon1;
        @BindView(R.id.circle_text_name1)
        TextView circleTextName1;
        @BindView(R.id.circle_text_time1)
        TextView circleTextTime1;
        @BindView(R.id.top_relativity)
        RelativeLayout topRelativity;
        @BindView(R.id.circle_text_title1)
        TextView circleTextTitle1;
        @BindView(R.id.circle_recycler1)
        RecyclerView circleRecycler1;
        @BindView(R.id.circle_likeview)
        LikeView circleLikeview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_icon2)
        SimpleDraweeView circleIcon2;
        @BindView(R.id.circle_text_name2)
        TextView circleTextName2;
        @BindView(R.id.circle_text_time2)
        TextView circleTextTime2;
        @BindView(R.id.top_relativity)
        RelativeLayout topRelativity;
        @BindView(R.id.circle_text_title2)
        TextView circleTextTitle2;
        @BindView(R.id.circle_recycler2)
        RecyclerView circleRecycler2;
        @BindView(R.id.circle_likeview2)
        LikeView circleLikeview2;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
