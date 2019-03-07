package com.example.weidushangcheng.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.adapter.ListAllAdapter;
import com.example.weidushangcheng.fragment.list.ListAllFragment;
import com.example.weidushangcheng.fragment.list.ListFinishFragment;
import com.example.weidushangcheng.fragment.list.ListFuKuanFragment;
import com.example.weidushangcheng.fragment.list.ListPingJiaFragment;
import com.example.weidushangcheng.fragment.list.ListShouHuoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragment extends Fragment {
    @BindView(R.id.list_tablelayout)
    TabLayout listTablelayout;
    @BindView(R.id.lis_viewgroup)
    ViewPager lisViewgroup;
    Unbinder unbinder;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"全部订单", "代付款", "待收货", "待评价", "已完成"};
    private ListAllAdapter listAllAdapter;
    private int[] pics = {R.mipmap.list_alllist, R.mipmap.list_fukuan, R.mipmap.list_shouhuo, R.mipmap.list_pingjia, R.mipmap.list_wancheng};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        fragments.add(new ListAllFragment());
        fragments.add(new ListFuKuanFragment());
        fragments.add(new ListShouHuoFragment());
        fragments.add(new ListPingJiaFragment());
        fragments.add(new ListFinishFragment());

        for (int i = 0; i < titles.length; i++) {
            listTablelayout.addTab(listTablelayout.newTab());
        }

        listTablelayout.setupWithViewPager(lisViewgroup, false);
        listAllAdapter = new ListAllAdapter(getActivity().getSupportFragmentManager(), fragments);
        lisViewgroup.setAdapter(listAllAdapter);

        for (int i = 0; i < titles.length; i++) {
            listTablelayout.getTabAt(i).setText(titles[i]);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
