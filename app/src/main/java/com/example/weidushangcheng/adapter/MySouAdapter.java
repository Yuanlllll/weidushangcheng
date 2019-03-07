package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.SouSuoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySouAdapter extends RecyclerView.Adapter<MySouAdapter.ViewHolder>{
    private Context context;
    private List<SouSuoBean.ResultBean> beanResult;

    public MySouAdapter(Context context, List<SouSuoBean.ResultBean> beanResult) {
        this.context = context;
        this.beanResult = beanResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sou_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Uri uri = Uri.parse(beanResult.get(i).getMasterPic());
        viewHolder.simpleView.setImageURI(uri);
        viewHolder.textName.setText(beanResult.get(i).getCommodityName());
        viewHolder.textPrice.setText("￥"+beanResult.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return beanResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simple_view)
        SimpleDraweeView simpleView;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_price)
        TextView textPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
