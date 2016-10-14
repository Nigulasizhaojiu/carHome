package com.example.dllo.recommend.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;
import com.example.dllo.carhome.WebViewActivity;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.example.dllo.recommend.ReUrlBean;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 */
public class OtherFragment extends BaseFragment {


    private PullToRefreshListView lv;
    private int position;
    private String url;
    private OtherAdapter otherAdapter;

    public static OtherFragment newInstance(int position) {

        List<String>urls = ReUrlBean.getUrls();
        Bundle args = new Bundle();
        args.putString("url",urls.get(position));
        args.putInt("position",position);
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.news_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_other);
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        position = args.getInt("position");
        url = args.getString("url");
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        otherAdapter = new OtherAdapter(mContext,position);
        lv.setAdapter(otherAdapter);
        initInternet();
    }

    private void initInternet() {
        if (position >= 6){
                GsonRequest<OtherBean> gsonRequest = new GsonRequest<OtherBean>(url,
                        OtherBean.class,
                        new Response.Listener<OtherBean>() {
                            @Override
                            public void onResponse(OtherBean response) {
                                otherAdapter.setOtherBean(response);
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
                    GsonRequest<OtherBean> gsonRequest = new GsonRequest<OtherBean>(url,
                            OtherBean.class,
                            new Response.Listener<OtherBean>() {
                                @Override
                                public void onResponse(OtherBean response) {
                                    otherAdapter.setOtherBean(response);
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
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    int urlId = 0;
                    OtherBean otherBean = (OtherBean) adapterView.getItemAtPosition(i);
                    urlId = otherBean.getResult().getNewslist().get(i-1).getId();
                    String urls = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n"+ urlId + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
                    intent.putExtra("urlWv",urls);
                    getActivity().startActivity(intent);
                }
            });

        }
    }
}
