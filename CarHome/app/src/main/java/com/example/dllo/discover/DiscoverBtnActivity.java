package com.example.dllo.discover;

import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/28.
 */
public class DiscoverBtnActivity extends BaseAty{
    TextView bigTitleOne,bigTitleTwo,bigTitleThree,bigTitleFour;
    TextView tvHotOne,tvHotTwo,tvHotThree,tvHotFour,tvHotFive,tvBuyOne,tvBuyTwo,tvBuyThree,tvBuyFour,tvBuyFive;
    TextView tvServiceOne,tvServiceTwo,tvServiceThree,tvServiceFour,tvToolOne,tvToolTwo,tvToolThree;
    ImageView ivHotOne,ivHotTwo,ivHotThree,ivHotFour,ivHotFive,ivBuyOne,ivBuyTwo,ivBuyThree,ivBuyFour,ivBuyFive;
    ImageView ivServiceOne,ivServiceTwo,ivServiceThree,ivServiceFour,ivToolOne,ivToolTwo,ivToolThree;
    String url = "http://mobile.app.autohome.com.cn/discover_v7.1.0/mobile/entrylist.ashx?pm=2&a=2&v=7.1.0";
    @Override
    protected int setLayout() {

        return R.layout.discover_btn_activity;
    }

    @Override
    protected void initView() {
        bigTitleOne = bindView(R.id.title_big_tv_hot);
        bigTitleTwo = bindView(R.id.title_big_tv_buy);
        bigTitleThree = bindView(R.id.title_big_tv_service);
        bigTitleFour = bindView(R.id.title_big_tv_tool);
        tvHotOne = bindView(R.id.tv_one_hot);
        tvHotTwo = bindView(R.id.tv_two_hot);
        tvHotThree = bindView(R.id.tv_three_hot);
        tvHotFour = bindView(R.id.tv_four_hot);
        tvHotFive = bindView(R.id.tv_five_hot);
        tvBuyOne = bindView(R.id.tv_one_buy);
        tvBuyTwo = bindView(R.id.tv_two_buy);
        tvBuyThree = bindView(R.id.tv_three_buy);
        tvBuyFour = bindView(R.id.tv_four_buy);
        tvBuyFive = bindView(R.id.tv_five_buy);
        tvServiceOne = bindView(R.id.tv_one_service);
        tvServiceTwo = bindView(R.id.tv_two_service);
        tvServiceThree = bindView(R.id.tv_three_service);
        tvServiceFour = bindView(R.id.tv_four_service);
        tvToolOne = bindView(R.id.tv_one_tool);
        tvToolTwo = bindView(R.id.tv_two_tool);
        tvToolThree = bindView(R.id.tv_three_tool);
        ivHotOne = bindView(R.id.iv_one_hot);
        ivHotTwo = bindView(R.id.iv_two_hot);
        ivHotThree = bindView(R.id.iv_three_hot);
        ivHotFour = bindView(R.id.iv_four_hot);
        ivHotFive = bindView(R.id.iv_five_hot);
        ivBuyOne = bindView(R.id.iv_one_buy);
        ivBuyTwo = bindView(R.id.iv_two_buy);
        ivBuyThree = bindView(R.id.iv_three_buy);
        ivBuyFour = bindView(R.id.iv_four_buy);
        ivBuyFive = bindView(R.id.iv_five_buy);
        ivServiceOne = bindView(R.id.iv_one_service);
        ivServiceTwo = bindView(R.id.iv_two_service);
        ivServiceThree = bindView(R.id.iv_three_service);
        ivServiceFour = bindView(R.id.iv_four_service);
        ivToolOne = bindView(R.id.iv_one_tool);
        ivToolTwo = bindView(R.id.iv_two_tool);
        ivToolThree = bindView(R.id.iv_three_tool);
    }

    @Override
    protected void initData() {
        GsonRequest<DiscoverActivityBean> gsonRequest = new GsonRequest<DiscoverActivityBean>(url, DiscoverActivityBean.class, new Response.Listener<DiscoverActivityBean>() {
            @Override
            public void onResponse(DiscoverActivityBean response) {
                initInternet(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    protected void initInternet(DiscoverActivityBean response){
        bigTitleOne.setText(response.getResult().get(0).getTitle());
        bigTitleTwo.setText(response.getResult().get(1).getTitle());
        bigTitleThree.setText(response.getResult().get(2).getTitle());
        bigTitleFour.setText(response.getResult().get(3).getTitle());
        tvHotOne.setText(response.getResult().get(0).getEntrylist().get(0).getTitle());
        tvHotTwo.setText(response.getResult().get(0).getEntrylist().get(1).getTitle());
        tvHotThree.setText(response.getResult().get(0).getEntrylist().get(2).getTitle());
        tvHotFour.setText(response.getResult().get(0).getEntrylist().get(3).getTitle());
        tvHotFive.setText(response.getResult().get(0).getEntrylist().get(4).getTitle());
        tvBuyOne.setText(response.getResult().get(1).getEntrylist().get(0).getTitle());
        tvBuyTwo.setText(response.getResult().get(1).getEntrylist().get(1).getTitle());
        tvBuyThree.setText(response.getResult().get(1).getEntrylist().get(2).getTitle());
        tvBuyFour.setText(response.getResult().get(1).getEntrylist().get(3).getTitle());
        tvBuyFive.setText(response.getResult().get(1).getEntrylist().get(4).getTitle());
        tvServiceOne.setText(response.getResult().get(2).getEntrylist().get(0).getTitle());
        tvServiceTwo.setText(response.getResult().get(2).getEntrylist().get(1).getTitle());
        tvServiceThree.setText(response.getResult().get(2).getEntrylist().get(2).getTitle());
        tvServiceFour.setText(response.getResult().get(2).getEntrylist().get(3).getTitle());
        tvToolOne.setText(response.getResult().get(3).getEntrylist().get(0).getTitle());
        tvToolTwo.setText(response.getResult().get(3).getEntrylist().get(1).getTitle());
        tvToolThree.setText(response.getResult().get(3).getEntrylist().get(2).getTitle());
        Picasso.with(this).load(response.getResult().get(0).getEntrylist().get(0).getIconurl()).resize(65,65).centerCrop().into(ivHotOne);
        Picasso.with(this).load(response.getResult().get(0).getEntrylist().get(1).getIconurl()).resize(65,65).centerCrop().into(ivHotTwo);
        Picasso.with(this).load(response.getResult().get(0).getEntrylist().get(2).getIconurl()).resize(65,65).centerCrop().into(ivHotThree);
        Picasso.with(this).load(response.getResult().get(0).getEntrylist().get(3).getIconurl()).resize(65,65).centerCrop().into(ivHotFour);
        Picasso.with(this).load(response.getResult().get(0).getEntrylist().get(4).getIconurl()).resize(65,65).centerCrop().into(ivHotFive);
        Picasso.with(this).load(response.getResult().get(1).getEntrylist().get(0).getIconurl()).resize(65,65).centerCrop().into(ivBuyOne);
        Picasso.with(this).load(response.getResult().get(1).getEntrylist().get(1).getIconurl()).resize(65,65).centerCrop().into(ivBuyTwo);
        Picasso.with(this).load(response.getResult().get(1).getEntrylist().get(2).getIconurl()).resize(65,65).centerCrop().into(ivBuyThree);
        Picasso.with(this).load(response.getResult().get(1).getEntrylist().get(3).getIconurl()).resize(65,65).centerCrop().into(ivBuyFour);
        Picasso.with(this).load(response.getResult().get(1).getEntrylist().get(4).getIconurl()).resize(65,65).centerCrop().into(ivBuyFive);
        Picasso.with(this).load(response.getResult().get(2).getEntrylist().get(0).getIconurl()).resize(65,65).centerCrop().into(ivServiceOne);
        Picasso.with(this).load(response.getResult().get(2).getEntrylist().get(1).getIconurl()).resize(65,65).centerCrop().into(ivServiceTwo);
        Picasso.with(this).load(response.getResult().get(2).getEntrylist().get(2).getIconurl()).resize(65,65).centerCrop().into(ivServiceThree);
        Picasso.with(this).load(response.getResult().get(2).getEntrylist().get(3).getIconurl()).resize(65,65).centerCrop().into(ivServiceFour);
        Picasso.with(this).load(response.getResult().get(3).getEntrylist().get(0).getIconurl()).resize(65,65).centerCrop().into(ivToolOne);
        Picasso.with(this).load(response.getResult().get(3).getEntrylist().get(1).getIconurl()).resize(65,65).centerCrop().into(ivToolTwo);
        Picasso.with(this).load(response.getResult().get(3).getEntrylist().get(2).getIconurl()).resize(65,65).centerCrop().into(ivToolThree);


    }

}
