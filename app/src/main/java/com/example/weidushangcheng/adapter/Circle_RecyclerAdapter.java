package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.CircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Circle_RecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private CircleBean circleBean;
    private ViewHolder viewHolder;

    public Circle_RecyclerAdapter(Context context, CircleBean circleBean) {
        this.context = context;
        this.circleBean = circleBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.xrecyclerview_layout1, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
           ((ViewHolder) holder).circleText1Pl.setText(circleBean.getResult().get(position).getGreatNum());
            viewHolder.circleIconShow2.setImageURI(Uri.parse(circleBean.getResult().get(position).getImage()));
            ((ViewHolder) holder).circleIconShow1.setImageURI(Uri.parse(circleBean.getResult().get(position).getImage()));
        }
    }

    @Override
    public int getItemCount() {
        return circleBean.getResult().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_text1_pl)
        TextView circleText1Pl;
        @BindView(R.id.circle_icon_show1)
        SimpleDraweeView circleIconShow1;
        @BindView(R.id.circle_icon_show2)
        SimpleDraweeView circleIconShow2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
