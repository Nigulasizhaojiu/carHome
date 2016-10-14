package com.example.dllo.recommend.recommends;

import android.content.Intent;
import android.view.LayoutInflater;
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
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/30.
 */
public class RecommendsFragment extends BaseFragment {

    private PullToRefreshListView lv;
    String url = ReUrl.RECOMMEND_URL;
    private Banner banner;

    @Override
    protected int setLayout() {
        return R.layout.recommends_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.iv_recommends);
    }

    @Override
    protected void initData() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        final RecommendsAdapter recommendsAdapter = new RecommendsAdapter(mContext);
        lv.setAdapter(recommendsAdapter);

        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.discover_banner,null);
        banner = new Banner(mContext);
        banner = (Banner) bannerView.findViewById(R.id.discover_banner);
        ListView listView = lv.getRefreshableView();
        listView.addHeaderView(bannerView);

        GsonRequest<RecommendsBean> gsonRequest = new GsonRequest<RecommendsBean>(url, RecommendsBean.class, new Response.Listener<RecommendsBean>() {
            @Override
            public void onResponse(RecommendsBean response) {
                recommendsAdapter.setRecommendsBean(response);
                ArrayList<String> images = new ArrayList<>();
                for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                    images.add(response.getResult().getFocusimg().get(i).getImgurl());
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                    banner.setIndicatorGravity(BannerConfig.RIGHT);
                    banner.setImages(images);
                }
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
                GsonRequest<RecommendsBean> gsonRequest = new GsonRequest<RecommendsBean>(url, RecommendsBean.class, new Response.Listener<RecommendsBean>() {
                    @Override
                    public void onResponse(RecommendsBean response) {
                        recommendsAdapter.setRecommendsBean(response);
                        ArrayList<String> images = new ArrayList<>();
                        for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                            images.add(response.getResult().getFocusimg().get(i).getImgurl());
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            banner.setIndicatorGravity(BannerConfig.RIGHT);
                            banner.setImages(images);
                            lv.onRefreshComplete();
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
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                int urlId = 0;
                RecommendsBean recommendsBean = (RecommendsBean) adapterView.getItemAtPosition(i);
                urlId = recommendsBean.getResult().getNewslist().get(i-1).getId();
                String urls = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n"+ urlId + "lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
                intent.putExtra("urlWv",urls);
                getActivity().startActivity(intent);
            }
        });
    }

}
