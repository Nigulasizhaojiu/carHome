package com.example.dllo.recommend.tv;

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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/9/30.
 */
public class TvFragment extends BaseFragment {

    private PullToRefreshListView lv;
    String url = "http://app.api.autohome.com.cn/autov4.8.8/news/videolist-pm1-vt0-s30-lastid0.json";

    @Override
    protected int setLayout() {
        return R.layout.tv_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_tv);
    }

    @Override
    protected void initData() {
        final TvAdapter tvAdapter = new TvAdapter(mContext);
        lv.setAdapter(tvAdapter);
        GsonRequest<TvBean> gsonRequest = new GsonRequest<TvBean>(url,
                TvBean.class,
                new Response.Listener<TvBean>() {
                    @Override
                    public void onResponse(TvBean response) {
                        tvAdapter.setTvBean(response);
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
                GsonRequest<TvBean> gsonRequest = new GsonRequest<TvBean>(url,
                        TvBean.class,
                        new Response.Listener<TvBean>() {
                            @Override
                            public void onResponse(TvBean response) {
                                tvAdapter.setTvBean(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                lv.onRefreshComplete();
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
                TvBean tvBean = (TvBean) adapterView.getItemAtPosition(i);
                String urls = tvBean.getResult().getList().get(i).getShareaddress();
                intent.putExtra("urlWv",urls);
                getActivity().startActivity(intent);
            }
        });
    }

}
