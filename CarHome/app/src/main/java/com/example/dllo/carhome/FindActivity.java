package com.example.dllo.carhome;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.example.dllo.sql.DBTools;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/10/11.
 */
public class FindActivity extends BaseAty {

    private EditText etFind;
    private ImageView ivClose;
    private TextView tvFinish;
    private ListView lv;
    private WebView wv;
    private DBTools tools;
    private LinearLayout ll;
    private ImageView ivDelete;
    private TextView tvDelete;
    private ArrayList<HistoryBean> historyBeen;
    private HistoryAdapter historyAdapter;
    private ListView lvh;
    private ImageView share;
    private LinearLayout llWeb;

    @Override
    protected int setLayout() {
        return R.layout.find_activity;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        ivClose = bindView(R.id.iv_find_activity);
        etFind = bindView(R.id.et_find_activity);
        tvFinish = bindView(R.id.tv_find_activity);
        lv = bindView(R.id.find_lisrview);
        wv = bindView(R.id.search_activity_web);
        ll = bindView(R.id.ll_find_aty);
        ivDelete = bindView(R.id.iv_delete_history);
        tvDelete = bindView(R.id.tv_delete_history);
        lvh = bindView(R.id.findh_lisrview);
        share = bindView(R.id.share);
        llWeb = bindView(R.id.ll_web);
    }

    @Override
    protected void initData() {
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });



        tools = new DBTools(this);
        historyBeen = tools.queryAllDB();
        historyAdapter = new HistoryAdapter(this);
        if (historyBeen.size() > 0) {
            ll.setVisibility(View.VISIBLE);
            lvh.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        } else {
            ll.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            lvh.setVisibility(View.GONE);
        }
        lvh.setAdapter(historyAdapter);
        historyAdapter.setHistoryBean(historyBeen);

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.deleteAllDB();
                historyBeen.clear();
                ll.setVisibility(View.GONE);
                lvh.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);

                lvh.setAdapter(historyAdapter);
                historyAdapter.setHistoryBean(historyBeen);
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.deleteAllDB();
                historyBeen.clear();

                ll.setVisibility(View.GONE);
                lvh.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);

                lvh.setAdapter(historyAdapter);
                historyAdapter.setHistoryBean(historyBeen);
            }
        });


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFind.setText("");
                if (historyBeen.size() > 0) {
                    if (wv.getVisibility() == View.VISIBLE){
                        ll.setVisibility(View.GONE);
                    }else {
                        ll.setVisibility(View.VISIBLE);
                    }
                    lvh.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                }
                else {
                    ll.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    lvh.setVisibility(View.GONE);
                }
                lvh.setAdapter(historyAdapter);
                historyAdapter.setHistoryBean(historyBeen);
            }
        });

        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    ivClose.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    if (historyBeen.size() > 0) {
                        ll.setVisibility(View.VISIBLE);
                        lvh.setVisibility(View.VISIBLE);
                    } else {
                        ll.setVisibility(View.GONE);
                        lvh.setVisibility(View.GONE);
                    }
                    lvh.setAdapter(historyAdapter);
                    historyAdapter.setHistoryBean(historyBeen);
                } else {
                    wv.setVisibility(View.GONE);
                    llWeb.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    ivClose.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    lvh.setVisibility(View.GONE);
                }
                innitRequestInternet(editable);
            }
        });
        lvh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strH = EncodeUtil.encode(historyBeen.get(i).getName());
                Log.d("FindActivity", "i:" + i);
                Log.d("FindActivity", historyBeen.get(i).getName());
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + strH + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                String carNameH = historyBeen.get(i).getName();
                etFind.setText(carNameH);

                wv.setVisibility(View.VISIBLE);
                llWeb.setVisibility(View.VISIBLE);
                wv.loadUrl(searchUrl);
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                WebSettings settings = wv.getSettings();
                settings.setJavaScriptEnabled(true);
            }
        });



    }

    private void innitRequestInternet(Editable s) {
        if (!s.toString().equals("")) {


            String str = s.toString();
            String url = "http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + str + "&t=4";
            GsonRequest<FindatyBean> gsonrequest = new GsonRequest<FindatyBean>(url, FindatyBean.class, new Response.Listener<FindatyBean>() {
                @Override
                public void onResponse(FindatyBean response) {
                    FindatyAdapter findatyAdapter = new FindatyAdapter(FindActivity.this);
                    findatyAdapter.setFindatyBean(response);
                    lv.setAdapter(findatyAdapter);
                    initClicklisener(response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleySingleton.getInstance().addRequest(gsonrequest);

        }
    }

    private void initClicklisener(final FindatyBean response) {
        if (!etFind.getText().toString().equals("")) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String str = EncodeUtil.encode(response.getResult().getWordlist().get(i).getName());
                    String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                    String carName = response.getResult().getWordlist().get(i).getName();
                    etFind.setText(carName);

                    wv.setVisibility(View.VISIBLE);
                    llWeb.setVisibility(View.VISIBLE);
                    wv.loadUrl(searchUrl);
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });

                    WebSettings settings = wv.getSettings();
                    settings.setJavaScriptEnabled(true);
                    HistoryBean historyBean = new HistoryBean(carName);
                    historyBean.setName(carName);
                    tools.insertDB(historyBean);

                }
            });
        }

        }
    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


}
