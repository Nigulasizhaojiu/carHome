package com.example.dllo.recommend.goodcreate;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;

/**
 * Created by dllo on 16/9/30.
 */
public class GoodCreatedFragment extends BaseFragment {

    private LinearLayout ll;
    private View inView;

    @Override
    protected int setLayout() {
        return R.layout.goodcreated_fragment;
    }

    @Override
    protected void initView() {
        inView = LayoutInflater.from(mContext).inflate(R.layout.goodcreated,null);
        ll = bindView(R.id.ll_pup);
    }

    @Override
    protected void initData() {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GoodCreatedFragment", "fafafafa");
                showPopupWindow();
            }
        });
    }


    private void showPopupWindow(){
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_goodcreate, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(false);
        mPopWindow.showAsDropDown(inView);
    }

}
