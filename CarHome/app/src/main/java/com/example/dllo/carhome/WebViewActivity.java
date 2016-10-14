package com.example.dllo.carhome;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.dllo.sql.DBTools;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/10/11.
 */
public class WebViewActivity extends BaseAty {
    private WebView wv;
    private ImageView ivWebShare,getIvWebConnection;
    private String url;
    private DBTools tools;

    @Override
    protected int setLayout() {
        return R.layout.wv_activity;
    }

    @Override
    protected void initView() {
        wv = bindView(R.id.wv_aty);
        ivWebShare = bindView(R.id.share_web_aty);
        getIvWebConnection = bindView(R.id.collection_web_aty);
    }

    @Override
    protected void initData() {

        ivWebShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });

        Intent intent = getIntent();
        url = intent.getStringExtra("urlWv");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
// text是分享文本，所有平台都需要这个字段
        oks.setText("0.0");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("谁说的?");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(this);
    }
}
