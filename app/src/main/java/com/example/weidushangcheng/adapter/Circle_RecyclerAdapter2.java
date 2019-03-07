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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Circle_RecyclerAdapter2 extends RecyclerView.Adapter {
    private Context context;
    private CircleBean circleBean;

    public Circle_RecyclerAdapter2(Context context, CircleBean circleBean) {
        this.context = context;
        this.circleBean = circleBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.xrecyclerview_layout2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).circleText2Pl.setText(circleBean.getResult().get(position).getContent());
            ((ViewHolder) holder).circleIcon2Show1.setImageURI(Uri.parse(circleBean.getResult().get(position).getImage()));
            ((ViewHolder) holder).circleIcon2Show2.setImageURI(Uri.parse(circleBean.getResult().get(position).getImage()));
            ((ViewHolder) holder).circleIcon2Show3.setImageURI(Uri.parse(circleBean.getResult().get(position).getImage()));
        }
    }

    @Override
    public int getItemCount() {
        return circleBean.getResult().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_text2_pl)
        TextView circleText2Pl;
        @BindView(R.id.circle_icon2_show1)
        ImageView circleIcon2Show1;
        @BindView(R.id.circle_icon2_show2)
        ImageView circleIcon2Show2;
        @BindView(R.id.circle_icon2_show3)
        ImageView circleIcon2Show3;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
