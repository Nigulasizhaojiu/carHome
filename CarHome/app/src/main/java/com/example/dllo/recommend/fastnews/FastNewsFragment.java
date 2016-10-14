package com.example.dllo.recommend.fastnews;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.example.dllo.recommend.ReUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/9/30.
 */
public class FastNewsFragment extends BaseFragment {

    private PullToRefreshListView lv;
    String url = ReUrl.FAST_NEWS_URL;

    @Override
    protected int setLayout() {

        return R.layout.fastnews_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_fastnews);
    }

    @Override
    protected void initData() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        final FastNewsAdapter fastNewsAdapter = new FastNewsAdapter(mContext);
        lv.setAdapter(fastNewsAdapter);
        GsonRequest<FastNewsBean> gsonRequest = new GsonRequest<FastNewsBean>(url, FastNewsBean.class, new Response.Listener<FastNewsBean>() {
            @Override
            public void onResponse(FastNewsBean response) {
                fastNewsAdapter.setFastNewsBean(response);
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
                GsonRequest<FastNewsBean> gsonRequest = new GsonRequest<FastNewsBean>(url, FastNewsBean.class, new Response.Listener<FastNewsBean>() {
                    @Override
                    public void onResponse(FastNewsBean response) {
                        fastNewsAdapter.setFastNewsBean(response);
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


    }
}
