package com.example.dllo.me;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/10/13.
 */
public class PushActivity extends BaseAty{

    private Switch perMessage;
    private Switch sysMessage;

    @Override
    protected int setLayout() {
        return R.layout.push_activity;
    }

    @Override
    protected void initView() {
        sysMessage = bindView(R.id.system_message);
        perMessage = bindView(R.id.person_message);
    }

    @Override
    protected void initData() {
        sysMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    JPushInterface.resumePush(getApplicationContext());
                }else {
                    JPushInterface.stopPush(getApplicationContext());
                }
            }
        });
    }
}
