package com.example.weidushangcheng;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weidushangcheng.fragment.CarFragment;
import com.example.weidushangcheng.fragment.CircleFragment;
import com.example.weidushangcheng.fragment.HomeFragment;
import com.example.weidushangcheng.fragment.ListFragment;
import com.example.weidushangcheng.fragment.MyFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tabbar)
    BottomTabBar bottomTabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabbar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(18)
                .setChangeColor(Color.RED,Color.BLACK)
                .setTabPadding(4,6,10)
                .addTabItem(" ",R.mipmap.common_tab_btn_home_n_hdpi,HomeFragment.class)
                .addTabItem("  ",R.mipmap.common_tab_btn_circle_n_hdpi,CircleFragment.class)
                .addTabItem("   ",R.mipmap.commom_tab_btn_shoppingcart_n_hdpi,CarFragment.class)
                .addTabItem("    ",R.mipmap.common_tab_btn_list_n_hdpi,ListFragment.class)
                .addTabItem("     ",R.mipmap.common_tab_btn_my_n_hdpi,MyFragment.class);
    }
}
