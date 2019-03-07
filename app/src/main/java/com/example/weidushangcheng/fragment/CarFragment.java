package com.example.weidushangcheng.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.weidushangcheng.CreationActivity;
import com.example.weidushangcheng.R;
import com.example.weidushangcheng.adapter.CarAdapter;
import com.example.weidushangcheng.base.BaseFragment;
import com.example.weidushangcheng.bean.CarBean;
import com.example.weidushangcheng.bean.ShopSelectListBean;
import com.example.weidushangcheng.fragment.fragmentmvp.car.presenter.CarPresenterImp;
import com.example.weidushangcheng.fragment.fragmentmvp.car.view.ICarView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CarFragment extends BaseFragment implements ICarView {

    @BindView(R.id.shopcart_recycler_view)
    RecyclerView shopRecy;
    @BindView(R.id.shop_box_all)
    CheckBox shopBoxAll;
    @BindView(R.id.shop_text_allprice)
    TextView shopTextAllprice;
    @BindView(R.id.shop_text_go)
    TextView shopTextGo;
    Unbinder unbinder;
    private CarPresenterImp carPresenterImp;
    private String sessionId;
    private int d;
    private CarAdapter carAdapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_car;
    }

    @Override
    protected void initData() {
        //绑定P
        carPresenterImp = new CarPresenterImp(this);
        //传值---把详情页面传的id传过来
        SharedPreferences preference = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        sessionId = preference.getString("sessionId", "");
        String userId = preference.getString("userId", "");
            d = Integer.parseInt(userId);
        carPresenterImp.getCarPresenter(d, sessionId);
        //初始化Recyclerview
        initRecy();
        //结算总价,数量
        getPriceCount();
        shopBoxAll.setChecked(false);
        //点击复选框进行判断
        onClickCheckAll();
        //点击跳转创建订单
        onClickCreation();
    }

    @Override
    protected void initView(View view) {

    }

    //创建订单
    private void onClickCreation() {
        shopTextGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String all_price = shopTextAllprice.getText().toString();
                if (!(all_price.equals("0")) && !(all_price.equals("0.0"))) {
                    Intent intent = new Intent(getActivity(), CreationActivity.class);
                    List<CarBean.ResultBean> creation_bill = new ArrayList<>();
                    for (int i = 0; i < shop_list.size(); i++) {
                        if (shop_list.get(i).isIscheck()) {
                            creation_bill.add(new CarBean.ResultBean(
                                    shop_list.get(i).getCommodityId(),
                                    shop_list.get(i).getCommodityName(),
                                    shop_list.get(i).getCount(),
                                    shop_list.get(i).getPic(),
                                    shop_list.get(i).getPrice()
                            ));
                        }
                        intent.putExtra("creation_bill", (Serializable) creation_bill);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void onClickCheckAll() {
        shopBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断全选时状态
                checkAll(shopBoxAll.isChecked());
                carAdapter.notifyDataSetChanged();
            }
        });
    }

    List<CarBean.ResultBean> shop_list = new ArrayList<>();

    private void checkAll(boolean checked) {
        double totalPrice = 0;
        int num = 0;

        for (int i = 0; i < shop_list.size(); i++) {
            //遍历商品，改变状态
            shop_list.get(i).setIscheck(checked);
            totalPrice = totalPrice + (shop_list.get(i).getPrice() * shop_list.get(i).getCount());
            num = num + shop_list.get(i).getCount();
        }

        if (checked) {
            shopTextAllprice.setText("" + totalPrice);
            shopTextGo.setText("去结算(" + num + ")");
        } else {
            shopTextAllprice.setText("0");
            shopTextGo.setText("去结算");
        }
    }

    private void getPriceCount() {
        carAdapter.setOnClick(new CarAdapter.ShopClick() {
            @Override
            public void shopPrice(List<CarBean.ResultBean> list) {
                //在这里看重新遍历已经改变状态后的数据
                //不可以break跳出,需计算商品价格和数量
                double totalprice = 0;
                //勾选商品数量,不是商品的购买数量
                int num = 0;
                //所有商品总数,和上面数量做对比,若相等,则全选
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    totalNum += list.get(i).getCount();
                    if (list.get(i).isIscheck()) {
                        totalprice += +list.get(i).getPrice() * list.get(i).getCount();
                        num += list.get(i).getCount();
                    }
                }
                if (num < totalNum) {
                    shopBoxAll.setChecked(false);
                } else {
                    shopBoxAll.setChecked(true);
                }
                shopTextAllprice.setText("" + totalprice);
                shopTextGo.setText("去结算(" + num + ")");
                if (list.size() == 0) {
                    shopBoxAll.setChecked(false);
                }
            }
        });

        //购物车商品删除
        carAdapter.setRemove(new CarAdapter.RemoveCallBack() {
            @Override
            public void removeposition(List<CarBean.ResultBean> list, int position) {
                //在这里重新遍历已经改变状态后的数据
                //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
                double totalPrice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    totalNum = totalNum + list.get(i).getCount();
                    if (list.get(i).isIscheck()) {
                        totalPrice = totalPrice + list.get(i).getPrice() * list.get(i).getCount();
                        num = num + list.get(i).getCount();
                    }

                }
                if (num < totalNum) {
                    shopBoxAll.setChecked(false);
                } else {
                    shopBoxAll.setChecked(true);
                }
                if (position != 0){
                    shop_list.remove(position);
                    shopTextAllprice.setText("" + totalPrice);
                    shopTextGo.setText("去结算(" + num + ")");
                }
                //添加购物车的集合
                List<ShopSelectListBean> addlist = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    int commodityId = list.get(i).getCommodityId();
                    int count = list.get(i).getCount();
                    addlist.add(new ShopSelectListBean(Integer.valueOf(commodityId), count));
                }
                String data = "[";
                for (ShopSelectListBean bean : addlist) {
                    data += "{\"commodityId\":" + bean.getCommodityId() + ",\"count\":" + bean.getCount() + "},";
                }
                String substring = data.substring(0, data.length() - 1);
                substring += "]";
                HashMap<String, String> params = new HashMap<>();
                params.put("data", substring);

                if (list.size() == 0) {
                    shopBoxAll.setChecked(false);
                }
            }
        });
    }

    private void initRecy() {
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shopRecy.setLayoutManager(linearLayoutManager);
        //适配器
        carAdapter = new CarAdapter(getActivity());
        shopRecy.setAdapter(carAdapter);
    }

    @Override
    public void getCarView(Object o) {
        if (o != null) {
            CarBean carBean = (CarBean) o;
            //Log.i("CarShuJu", "car" + carBean.getMessage());
            carAdapter.setList(carBean.getResult());
            shop_list = carBean.getResult();
            shopBoxAll.setChecked(false);
        }
    }

    //实时刷新
    @Override
    public void onResume() {
        super.onResume();
        //双击退出
        getFocus();
        carPresenterImp.getCarPresenter(d, sessionId);
    }

    private void getFocus() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
