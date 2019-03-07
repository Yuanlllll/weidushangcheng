package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidushangcheng.DetailsActivity;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

class Home_RecyclerAdapter03 extends RecyclerView.Adapter<Home_RecyclerAdapter03.ViewHolder> {
    private Context context;
    private HomeBean homeBean;
    private ViewHolder holder;

    public Home_RecyclerAdapter03(Context context, HomeBean homeBean) {
        this.context = context;
        this.homeBean = homeBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recyclerview_layout01, viewGroup, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        holder.home_text_price01.setText("¥"+homeBean.getResult().getPzsh().getCommodityList().get(i).getPrice()+"");
        holder.home_text_title01.setText(homeBean.getResult().getPzsh().getCommodityList().get(i).getCommodityName());
        //Glide.with(context).load(rb1Bean.getResult().getPzsh().get(0).getCommodityList().get(i).getMasterPic()).into(holder.view_image);
        Uri uri = Uri.parse(homeBean.getResult().getPzsh().getCommodityList().get(i).getMasterPic());
        holder.home_icon01.setImageURI(uri);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(homeBean.getResult().getPzsh().getCommodityList().get(i).getCommodityId()+"");
                context.startActivity(new Intent(context,DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeBean.getResult().getPzsh().getCommodityList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView home_icon01;
        private final TextView home_text_price01;
        private final TextView home_text_title01;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_icon01 = itemView.findViewById(R.id.home_icon01);
            home_text_price01 = itemView.findViewById(R.id.home_text_price01);
            home_text_title01 = itemView.findViewById(R.id.home_text_title01);
        }
    }
}
