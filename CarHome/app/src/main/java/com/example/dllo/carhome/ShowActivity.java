package com.example.dllo.carhome;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/10/11.
 */
public class ShowActivity extends BaseAty implements View.OnClickListener{
    private String URL_WELCOME ="http://url.cn/40IP3VW";
    private ImageView img;
    private TextView tv;
    Timer timer = new Timer();
    int time = 1;
    @Override
    protected int setLayout() {
        return R.layout.show_activity;
    }

    @Override
    protected void initView() {
        img = bindView(R.id.count_down_image);
        tv = bindView(R.id.count_down);
        tv.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        ininitRequestInternet();

        inittime();


    }

    private void inittime() {
        TimerTask task  = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        if (time < 0) {
                            timer.cancel();
                            Intent intent = new Intent(ShowActivity.this, MainActivity.class);
                            startActivity(intent);
                            ShowActivity.this.finish();


                        }
                    }
                });

            }
        };
        timer.schedule(task,1000,1000);
    }

    private void ininitRequestInternet() {
        GsonRequest<ShowBean> gsonRequest = new GsonRequest<ShowBean>(URL_WELCOME, ShowBean.class, new Response.Listener<ShowBean>() {
            @Override
            public void onResponse(ShowBean response) {
                if (response.getResult().getIshavead() !=0){
                    Picasso.with(ShowActivity.this).load(response.getResult().getAd().getImgad().getImgurl()).into(img);
                    tv.setVisibility(View.VISIBLE);
                }








            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.count_down:
                Intent intent = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
                this.finish();

                break;
        }

    }
}