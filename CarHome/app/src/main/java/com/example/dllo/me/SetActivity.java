package com.example.dllo.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;

/**
 * Created by dllo on 16/10/13.
 */
public class SetActivity extends BaseAty{

    private LinearLayout llPush;
    private LinearLayout mLlData;
    private TextView mTvData;

    @Override
    protected int setLayout() {
        return R.layout.set_activity;
    }

    @Override
    protected void initView() {
        llPush = bindView(R.id.ll_set_up_push);
        mLlData = bindView(R.id.ll_data);
        mTvData = bindView(R.id.tv_me_data);
    }

    @Override
    protected void initData() {
        //获取大小
        String files = null;
        try {
            files = DataCleanManager.getTotalCacheSize(SetActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTvData.setText(files);
        
        llPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this,PushActivity.class);
                startActivity(intent);
            }
        });
        mLlData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定删除缓存");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清除
                        DataCleanManager.clearAllCache(SetActivity.this);
                        try {
                            //获取大小
                            String file = DataCleanManager.getTotalCacheSize(SetActivity.this);
                            mTvData.setText(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }
}
