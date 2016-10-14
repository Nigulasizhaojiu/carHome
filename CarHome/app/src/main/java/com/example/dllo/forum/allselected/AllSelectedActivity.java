package com.example.dllo.forum.allselected;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
public class AllSelectedActivity extends BaseAty {

    private PullToRefreshListView lv;
    private String url;
    private int num = 0;


    @Override
    protected int setLayout() {
        return R.layout.allselected_activity;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_allselected);

    }

    @Override
    protected void initData() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        final AllSelectedAdapter allSelectedAdapter = new AllSelectedAdapter(this);
        lv.setAdapter(allSelectedAdapter);

        //接收跳转传的值从url集合中取对应位置的网址
        Intent intent = getIntent();
        int listposition = intent.getIntExtra("position",1);
        final String[] urls = this.getResources().getStringArray(R.array.urls);
        url = urls[listposition];



        GsonRequest<AllSelectedBean> gsonRequest = new GsonRequest<AllSelectedBean>(url, AllSelectedBean.class, new Response.Listener<AllSelectedBean>() {
            @Override
            public void onResponse(AllSelectedBean response) {
                allSelectedAdapter.setAllSelectedBean(response);
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
                GsonRequest<AllSelectedBean> gsonRequest = new GsonRequest<AllSelectedBean>(url, AllSelectedBean.class, new Response.Listener<AllSelectedBean>() {
                    @Override
                    public void onResponse(AllSelectedBean response) {
                        allSelectedAdapter.setAllSelectedBean(response);
                        lv.onRefreshComplete();
                        Toast.makeText(AllSelectedActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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
                num = num + 1;
                String urls = url.substring(0,url.length()-10) + num +"-s30.json";
                // Log.d("试试", url);
                //   forumListV.onRefreshComplete();

                GsonRequest<AllSelectedBean> gsonRequest  = new GsonRequest<AllSelectedBean>(urls, AllSelectedBean.class,
                        new Response.Listener<AllSelectedBean>() {
                            @Override
                            public void onResponse(AllSelectedBean response) {


                                allSelectedAdapter.setBean1(response);

                                lv.onRefreshComplete();



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);



            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AllSelectedActivity.this, WebViewActivity.class);
                int urlId = 0;
                AllSelectedBean allSelectedBean = (AllSelectedBean) adapterView.getItemAtPosition(i);
                urlId = allSelectedBean.getResult().getList().get(i-1).getTopicid();
                String urls = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"+ urlId + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                intent.putExtra("urlWv",urls);
                startActivity(intent);
            }
        });


    }
}
