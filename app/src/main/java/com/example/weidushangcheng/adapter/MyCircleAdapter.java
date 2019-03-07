package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.CircleBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhuang.likeviewlibrary.LikeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCircleAdapter extends XRecyclerView.Adapter<MyCircleAdapter.ViewHolder>{
    private Context context;
    private List<CircleBean.ResultBean> circleBeanResult;


    public MyCircleAdapter(Context context, List<CircleBean.ResultBean> circleBeanResult) {
        this.context = context;
        this.circleBeanResult = circleBeanResult;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circle_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.circleTextoneName.setText(circleBeanResult.get(i).getNickName());
        //Glid圆形图片
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(circleBeanResult.get(i).getHeadPic()).apply(requestOptions).into(viewHolder.circleOneImg);
        RoundedCorners roundedCorners= new RoundedCorners(20);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).load(circleBeanResult.get(i).getImage()).apply(options).into(viewHolder.oneImg1);
        Long dateLong = Long.valueOf(circleBeanResult.get(i).getCreateTime());
        String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
        viewHolder.circleTextoneTime.setText(time);
        viewHolder.circleTextoneTitle.setText(circleBeanResult.get(i).getContent());
        viewHolder.item01LikeView.setLikeCount(Integer.parseInt(circleBeanResult.get(i).getGreatNum()));
    }


    @Override
    public int getItemCount() {
        return circleBeanResult.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_one_img)
        ImageView circleOneImg;
        @BindView(R.id.circle_textone_name)
        TextView circleTextoneName;
        @BindView(R.id.circle_textone_time)
        TextView circleTextoneTime;
        @BindView(R.id.top_relativity)
        RelativeLayout topRelativity;
        @BindView(R.id.circle_textone_title)
        TextView circleTextoneTitle;
        @BindView(R.id.one_img1)
        ImageView oneImg1;
        @BindView(R.id.item01_like_view)
        LikeView item01LikeView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
