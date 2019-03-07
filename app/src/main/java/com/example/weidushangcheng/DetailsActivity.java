package com.example.weidushangcheng;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidushangcheng.adapter.DetailsAdapter;
import com.example.weidushangcheng.base.BaseActivity;
import com.example.weidushangcheng.bean.CarBean;
import com.example.weidushangcheng.bean.ShopDetails;
import com.example.weidushangcheng.bean.ShopDetailsBean;
import com.example.weidushangcheng.bean.ShopSelectListBean;
import com.example.weidushangcheng.bean.TongBuCart;
import com.example.weidushangcheng.bean.TongBuCartBean;
import com.example.weidushangcheng.mvp.addcart.addpresenter.AddPresenter;
import com.example.weidushangcheng.mvp.addcart.view.IAddView;
import com.example.weidushangcheng.mvp.details.presenter.DetailsPresenter;
import com.example.weidushangcheng.mvp.details.view.IDetailsView;
import com.example.weidushangcheng.network.DensityUtil;
import com.example.weidushangcheng.network.StatusBarUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DetailsActivity extends BaseActivity implements IDetailsView, IAddView {

    @BindView(R.id.l_recycler_view)
    LRecyclerView lRecyclerView;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.titlel)
    TextView titlel;
    @BindView(R.id.titler)
    TextView titler;
    @BindView(R.id.button)
    LinearLayout button;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.btn_add)
    ImageButton btnAdd;
    @BindView(R.id.btn_buy)
    ImageButton btnBuy;
    private float totaldy;
    private float mRecyclerFactor;
    private List<ShopDetailsBean> list;
    private int item1 = 0;
    private int item2 = 0;
    private int item3 = 0;
    private LinearLayoutManager manager;
    private Resources res;
    private DetailsPresenter detailsPresenter;
    private String id;
    private AddPresenter addPresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        //点击返回
        leftClick();
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        //详情
        detailsPresenter = new DetailsPresenter(DetailsActivity.this);
        detailsPresenter.getPresenterData(id);
        //添加购物车--在添加之前先查询购物车
        addPresenter = new AddPresenter(DetailsActivity.this);
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ShopDetailsBean bean = new ShopDetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        //传数据,同步
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences preference = getSharedPreferences("config", MODE_PRIVATE);
                    String userId = preference.getString("userId", "");
                    String sessionId = preference.getString("sessionId", "");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("commodityId", id);
                    jsonObject.put("count", 1);
                    JSONArray jsonArray = new JSONArray();
                    JSONArray array = jsonArray.put(jsonObject);
                    String s = array.toString();
                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), s);
                    addPresenter.getTbPresenter(userId, sessionId, requestBody);
                    //Log.i("tongbu123", "onClick: " + userId + sessionId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void leftClick() {
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe(sticky = true)
    public void eventBusReceiver(String commodityId) {
        id = commodityId;
    }

    //详情
    @Override
    public void getDetails(Object o) {
        if (o != null) {
            ShopDetails shopDetails = (ShopDetails) o;
            ShopDetails.ResultBean detailsResult = shopDetails.getResult();
            setAdapter(list, detailsResult);
        }
    }

    private void setAdapter(List<ShopDetailsBean> list, ShopDetails.ResultBean detailsResult) {
        lRecyclerView.setNestedScrollingEnabled(false);
        DetailsAdapter adapter = new DetailsAdapter(this, detailsResult);
        adapter.setDataList(list);
        LRecyclerViewAdapter adapter1 = new LRecyclerViewAdapter(adapter);
        View headView = View.inflate(this, R.layout.details_head, null);
        adapter1.addHeaderView(headView);
        manager = new LinearLayoutManager(this);
        lRecyclerView.setLayoutManager(manager);
        lRecyclerView.setAdapter(adapter1);
        /*lrecyclerView.setPullRefreshEnabled(false);
        lrecyclerView.setLoadMoreEnabled(false);*/
        adapter.setListener(new DetailsAdapter.OnItemHeightListener() {
            @Override
            public void setOnItemHeightListener(int height, int type) {
                if (height != 0) {
                    if (type == 1001) {
                        item1 = (int) (height + mRecyclerFactor);
                    } else if (type == 1002) {
                        item2 = item1 + (height - DensityUtil.getWidth(DetailsActivity.this));
                    } else {
                        item3 = item2 + height;
                    }
                }

            }
        });

    }

    @Override
    protected void initListener() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item1 != 0) {
                    lRecyclerView.scrollBy(0, (int) -totaldy);
                }
            }
        });
        titlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item2 != 0) {
//                    判断滑动距离是否超过商品
                    if (totaldy > item1)

                        lRecyclerView.scrollBy(0, (int) -(totaldy - item1) + 20);
                    else
                        lRecyclerView.scrollBy(0, (int) (item1 - totaldy) + 20);

                }
            }
        });
        titler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item3 != 0) {
                    lRecyclerView.scrollBy(0, item2);
                }
            }
        });
        //        设置渐变的主要代码
        lRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);

                //滑动的距离
                totaldy += dy;
                if (item2 != 0 && item1 != 0 && item3 != 0) {
                    if (totaldy < item1) {
                        title.setTextColor(res.getColor(R.color.orange));
                        titlel.setTextColor(res.getColor(R.color.black));
                        titler.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item1 && totaldy < item2) {
                        titlel.setTextColor(res.getColor(R.color.orange));
                        title.setTextColor(res.getColor(R.color.black));
                        titler.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item2) {
                        titler.setTextColor(res.getColor(R.color.orange));
                        title.setTextColor(res.getColor(R.color.black));
                        titlel.setTextColor(res.getColor(R.color.black));
                    }
                }
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (totaldy <= mRecyclerFactor) {
//                    如果在显示图片中显示圆图标
//                    算出透明度
                    float scale = (float) totaldy / mRecyclerFactor;
                    float alpha = scale * 255;

                    if (alpha < 160) {
//                        如果透明度小于160设置为顶部是图片
                        button.setVisibility(View.GONE);
                        StatusBarUtil.setTranslucentForImageView(DetailsActivity.this, (int) alpha, titleBar);
                    } else {
                        button.setVisibility(View.VISIBLE);
                        StatusBarUtil.setColor(DetailsActivity.this, Color.argb((int) alpha, 255, 255, 255));
                    }
                    titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
//                   已经不显示图片
                    titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
                    button.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    protected void initTitle() {
        res = getResources();
        StatusBarUtil.setTranslucentForImageView(this, 0, titleBar);
        left.setBackgroundResource(R.mipmap.back_b);
//        right.setBackgroundResource(R.mipmap.add);
        right.setVisibility(View.GONE);
        icon.setVisibility(View.VISIBLE);
        //        图片的高度-状态栏的高度
        mRecyclerFactor = (DensityUtil.dp2px(this, 180.0F) - DensityUtil.getStatusBarHeight(this));

    }

    @Override
    public void Success(Object o) {
        TongBuCartBean tongBuCartBean = (TongBuCartBean) o;
        //Log.i("ml", "Success: " + tongBuCartBean.getMessage());
        Toast.makeText(DetailsActivity.this, tongBuCartBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }
}
