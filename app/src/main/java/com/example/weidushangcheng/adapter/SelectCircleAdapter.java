package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.bean.MyCircleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SelectCircleAdapter extends RecyclerView.Adapter<SelectCircleAdapter.ViewHolder>{
    private List<MyCircleBean.ResultBean> list;
    private Context context;

    public SelectCircleAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    //刷新列表且添加
    public void setList(List<MyCircleBean.ResultBean> mlist) {
        list.clear();
        if (mlist!=null){
            list.addAll(mlist);
        }
        notifyDataSetChanged();
    }

    public void addList(List<MyCircleBean.ResultBean> mlist) {
        if (mlist!=null){
            list.addAll(mlist);
        }
        notifyDataSetChanged();
    }
    public MyCircleBean.ResultBean getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xrecy_select_circle, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.getdata(getItem(i),context,i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView text_content;
        private TextView text_time;
        private TextView text_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.recy_item_circle_image);
            text_content=itemView.findViewById(R.id.recy_item_circle_text_content);
            text_time=itemView.findViewById(R.id.recy_item_circle_text_time);
            text_num=itemView.findViewById(R.id.recy_item_circle_text_num);
        }

        public void getdata(MyCircleBean.ResultBean item, Context context, int i) {

            if (item.getImage()!=null){
                String[] split = item.getImage().split("\\,");
                image.setImageURI(Uri.parse(split[0]));
            }
            text_content.setText(item.getContent());
            text_num.setText(item.getGreatNum()+"");
            String times = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(
                    new java.util.Date(item.getCreateTime()));
            text_time.setText(times);
        }
    }
}
