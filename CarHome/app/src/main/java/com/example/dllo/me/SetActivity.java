package com.example.dllo.me;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;

/**
 * Created by dllo on 16/10/13.
 */
public class SetActivity extends BaseAty{

    private LinearLayout llPush;

    @Override
    protected int setLayout() {
        return R.layout.set_activity;
    }

    @Override
    protected void initView() {
        llPush = bindView(R.id.ll_set_up_push);
    }

    @Override
    protected void initData() {
        llPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this,PushActivity.class);
                startActivity(intent);
            }
        });
    }
}
