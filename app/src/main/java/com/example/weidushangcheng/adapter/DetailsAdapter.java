package com.example.weidushangcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weidushangcheng.DetailsActivity;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.base.SuperViewHolder;
import com.example.weidushangcheng.bean.ShopDetails;
import com.example.weidushangcheng.bean.ShopDetailsBean;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情adapter
 */
public class DetailsAdapter extends ListBaseAdapter<ShopDetailsBean> {

    private int layoutID;
    private ShopDetails.ResultBean detailsResult;
    private int height = 0;
    private LinearLayout item;


    public DetailsAdapter(Context context, ShopDetails.ResultBean detailsResult) {
        super(context);
        this.detailsResult = detailsResult;
    }

    @Override
    public int getItemViewType(int position) {
        int type = mDataList.get(position).getType();
        if (type == 1) {
//            商品
            layoutID = R.layout.item_details1;
            return 1001;
        } else if (type == 2) {
//            详情
            layoutID = R.layout.item_details2;
            return 1002;
        } else if (type == 3) {
//            评价
            layoutID = R.layout.item_details3;
            return 1003;
        } else if (type == 4) {
//            评价
            layoutID = R.layout.item_details4;
            return 1004;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getLayoutId() {
        return layoutID;
    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1001) {
            List<String> list = new ArrayList<>();
            final LinearLayout item = holder.getView(R.id.item);
            FlyBanner flybanner = item.findViewById(R.id.detail_fly_banner);
            TextView detailName = item.findViewById(R.id.detail_name);
            TextView detailsKg = item.findViewById(R.id.details_kg);
            TextView detail_describe = item.findViewById(R.id.detail_describe);
            detail_describe.setText(detailsResult.getDescribe());
            detailName.setText(detailsResult.getCategoryName());
            detailsKg.setText(detailsResult.getWeight() + "kg");
            for (int i = 0; i < 5; i++) {
                list.add(detailsResult.getPicture().split(",")[i]);
            }
            flybanner.setImagesUrl(list);

            getMeasureHeight(item, type);
        }
        if (type == 1003) {
            final LinearLayout item = holder.getView(R.id.item);
            TextView details_commentNum = item.findViewById(R.id.details_commentNum);
            details_commentNum.setText("(" + detailsResult.getCommentNum() + ")");
            getMeasureHeight(item, type);
        }
        if (type == 1004) {
            final LinearLayout item = holder.getView(R.id.item);

            getMeasureHeight(item, type);
        }
        if (type == 1002) {
            WebView webView = holder.getView(R.id.webView);
            WebSettings mSetting = webView.getSettings();
            mSetting.setJavaScriptEnabled(true);
            String js = "<script type=\"text/javascript\">" +
                    "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                    "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                    "imgs[i].style.width = '100%';" +  // 宽度改为100%
                    "imgs[i].style.height = 'auto';" +
                    "}" +
                    "</script>";

            mSetting.setBlockNetworkImage(false);//解决图片不显示
            mSetting.setBuiltInZoomControls(true); // 显示放大缩小
            mSetting.setSupportZoom(false);
            //url = url.replaceAll("<img", "<img style='width:100%'");
            webView.loadDataWithBaseURL(null, detailsResult.getDetails() + js, "text/html", "utf-8", null);
            getMeasureHeight(webView, type);

        }
    }

    /**
     * 获取每个item的高度
     *
     * @param view item的跟布局
     * @param type 用于判断是那个item的高度
     */
    public void getMeasureHeight(final View view, final int type) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (listener != null) {
                    if (type == 1003 || type == 1004) {
                        if (height != 0) {
                            height += view.getHeight();
                            listener.setOnItemHeightListener(height, type);
                        } else {
                            height = view.getHeight();
                        }
                    } else {
                        listener.setOnItemHeightListener(view.getHeight(), type);
                    }

                }
            }
        });
    }

    public interface OnItemHeightListener {
        void setOnItemHeightListener(int height, int type);
    }

    private OnItemHeightListener listener;

    public void setListener(OnItemHeightListener listener) {
        this.listener = listener;
    }
}

