package com.example.dllo.carhome;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;


/**
 * Created by dllo on 16/9/19.
 */
public class ShowActivity extends BaseAty {

    private ImageView countDownImage;
    private TextView countDown;
    private InputStream is;
    private HttpURLConnection connection;

    @Override
    protected int setLayout() {
        return R.layout.show_activity;
    }

    @Override
    protected void initView() {
        countDownImage = bindView(R.id.count_down_image);
        countDown = bindView(R.id.count_down);
        countDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                async.cancel(true);
                Intent intent = new Intent(ShowActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }


}
