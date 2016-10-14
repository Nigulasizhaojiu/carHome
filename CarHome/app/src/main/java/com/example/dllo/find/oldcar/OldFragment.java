package com.example.dllo.find.oldcar;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;

/**
 * Created by dllo on 16/9/27.
 */
public class OldFragment extends BaseFragment {
    String urls = "http://m.che168.com/dalian/list/?sourcename=mainapp&safe=0&carsafe=1&pvareaid=102254";
    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.newcar_fragment;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.old_car_web);
    }

    @Override
    protected void initData() {
        webView.loadUrl(urls);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
