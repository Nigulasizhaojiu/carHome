package com.example.dllo.recommend.say;

import android.content.Intent;
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
import com.example.dllo.recommend.ReUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/9/30.
 */
public class SayFragment extends BaseFragment {

    private PullToRefreshListView lv;
    String url = ReUrl.SAY_URL;
    @Override
    protected int setLayout() {
        return R.layout.say_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_say);
    }

    @Override
    protected void initData() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        final SayAdapter sayAdapter = new SayAdapter(mContext);
        lv.setAdapter(sayAdapter);
        GsonRequest<SayBean> gsonRequest = new GsonRequest<SayBean>(url, SayBean.class, new Response.Listener<SayBean>() {
            @Override
            public void onResponse(SayBean response) {
                sayAdapter.setSayBean(response);
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
                GsonRequest<SayBean> gsonRequest = new GsonRequest<SayBean>(url, SayBean.class, new Response.Listener<SayBean>() {
                    @Override
                    public void onResponse(SayBean response) {
                        sayAdapter.setSayBean(response);
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
                SayBean sayBean = (SayBean) adapterView.getItemAtPosition(i);
                urlId = sayBean.getResult().getList().get(i-1).getId();
                String urls = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n"+ urlId + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
                intent.putExtra("urlWv",urls);
                getActivity().startActivity(intent);
            }
        });
    }

}
