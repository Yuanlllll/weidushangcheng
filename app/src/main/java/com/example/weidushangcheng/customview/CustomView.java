package com.example.weidushangcheng.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.weidushangcheng.R;
import com.example.weidushangcheng.adapter.CarAdapter;
import com.example.weidushangcheng.adapter.MyCircleAdapter;
import com.example.weidushangcheng.bean.CarBean;
import com.example.weidushangcheng.bean.ShopCartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomView extends RelativeLayout {
    @BindView(R.id.custom_del)
    ImageView customDel;
    @BindView(R.id.custom_edit_text)
    EditText customEditText;
    @BindView(R.id.custom_add)
    ImageView customAdd;
    private OnAddOrDelClickListener clickListener;
    private MyCircleAdapter circleAdapter;
    private CallBackListener call1BackListener;
    private List<ShopCartBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;
    int num = 0;

    public void setCallBackListener(CallBackListener callBackListener) {
        this.call1BackListener = callBackListener;
    }

    public void setClickListener(OnAddOrDelClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.custom_layout, null);
        ButterKnife.bind(this, view);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.edit_shopcart);
        String num = typedArray.getString(R.styleable.edit_shopcart_num);
        typedArray.recycle();
        customEditText.setText(num);
        addView(view);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(MyCircleAdapter circleAdapter, List<ShopCartBean.DataBean.ListBean> list, int i) {
        this.circleAdapter = circleAdapter;
        this.list = list;
        position = i;
        num = list.get(i).getNum();
        customEditText.setText(num + "");
    }

    @OnClick({R.id.custom_del, R.id.custom_add})
    public void onViewClicked(View view) {
        String string = customEditText.getText().toString();
        switch (view.getId()) {
            case R.id.custom_del:
                if (string.length() > 0) {
                   clickListener.onDelClick(view);
                    //call1BackListener.mycallBack();
                   // circleAdapter.notifyItemChanged(position);
                } else {
                    mToast();
                }
                break;
            case R.id.custom_add:
                if (string.length() > 0) {
                  clickListener.onAddClick(view);
                   // call1BackListener.mycallBack();
                    //circleAdapter.notifyItemChanged(position);
                } else {
                    mToast();
                }
                break;
        }
    }

    public void setNumber(int num) {
        if (num > 0) {
            customEditText.setText(num + "");
        } else {
            toast();
        }
    }

    public int getNumber() {
        String numStr = customEditText.getText().toString().trim();
        num = Integer.valueOf(numStr);
        return num;
    }

    private void toast() {
        Toast.makeText(getContext(), "不能为0", Toast.LENGTH_SHORT).show();
    }

    private void mToast() {
        Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
    }

    public interface OnAddOrDelClickListener {
        void onAddClick(View view);

        void onDelClick(View view);
    }

    public interface CallBackListener {
        void mycallBack();
    }


}
