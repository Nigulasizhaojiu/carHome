package com.example.dllo.forum.twentyfour;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;
import com.example.dllo.carhome.WebViewActivity;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/9/22.
 */
public class TwentyFourActivity extends BaseAty {

    String url = "http://183.232.160.141/club.app.autohome.com.cn/club_v7.0.5/club/shotfoumlist-pm2-p1-s50.json";
    private PullToRefreshListView lv;

    @Override
    protected int setLayout() {
        return R.layout.twentyfour_activity;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_twentyfour);

    }

    @Override
    protected void initData() {
        final TwentyFourAdapter adapter = new TwentyFourAdapter(this);
        lv.setAdapter(adapter);
        GsonRequest<TwentyFourBean> gsonRequest = new GsonRequest<TwentyFourBean>(url, TwentyFourBean.class, new Response.Listener<TwentyFourBean>() {
            @Override
            public void onResponse(TwentyFourBean response) {
                adapter.setTwentyFourBean(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                GsonRequest<TwentyFourBean> gsonRequest = new GsonRequest<TwentyFourBean>(url, TwentyFourBean.class, new Response.Listener<TwentyFourBean>() {
                    @Override
                    public void onResponse(TwentyFourBean response) {
                        adapter.setTwentyFourBean(response);
                        lv.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TwentyFourActivity.this, WebViewActivity.class);
                int urlId = 0;
                TwentyFourBean twentyFourBean = (TwentyFourBean) adapterView.getItemAtPosition(i);
                urlId = twentyFourBean.getResult().getList().get(i-1).getTopicid();
                String urls = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"+ urlId + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                intent.putExtra("urlWv",urls);
                startActivity(intent);
            }
        });


    }
}
