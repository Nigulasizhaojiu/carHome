package com.example.dllo.discover;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.FindActivity;
import com.example.dllo.carhome.R;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class DiscoverFragment extends BaseFragment {

    private PullToRefreshListView lv;
    String url = "http://223.99.255.20/mobile.app.autohome.com.cn/discover_v7.0.0/mobile/getcardlist.ashx?a=2&pm=1&v=7.0.0&uid=&deviceid=021676cd548e5cf2b6149c916a767228fac74da0&pid=0&cid=0&state=1&pageindex=1&pagesize=20&lat=0.000000&lng=0.000000&hid=";
    private RecyclerView rvOne;
    private TextView tvOne;
    private DiscoverRvHeadAdapter discoverRvHeadAdapter;
    private DiscoverAdapter adapter;
    private RecyclerView rvTwo;
    private TextView tvTwo;
    private DiscoverRvTwoHeadAdapter discoverRvTwoHeadAdapter;
    private TextView tvLove;
    private ImageView iv;
    private ImageView ivOne;
    private ImageView ivTwo;
    private ImageView ivThree;
    private TextView tvMoreHd;
    private TextView tvHd;
    private RecyclerView rvHor;
    private DiscoverRvHorHeadAdapter rvHorHeadAdapter;
    private TextView tvService;
    private TextView tvBuy;
    private ImageView ivServiceOne;
    private ImageView ivServiceTwo;
    private ImageView ivServiceThree;
    private ImageView ivServiceFour;
    private ImageView ivServiceFive;
    private ImageView ivServiceSix;
    private RecyclerView rvBtn;
    private DiscoverBtnHeadAdapter discoverBtnHeadAdapter;
    private ImageView ivSmall;
    private ImageView ivBtn;
    private Banner banner;
    private ImageView ivFind;


    @Override
    protected int setLayout() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.rv_discover);
        ivFind = bindView(R.id.iv_discover_find);
    }

    @Override
    protected void initData() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new DiscoverAdapter(mContext);
        lv.setAdapter(adapter);

        getHeadView();

        ivFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });

        GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(url, DiscoverBean.class, new Response.Listener<DiscoverBean>() {
            @Override
            public void onResponse(DiscoverBean response) {
                adapter.setDiscoverBean(response);
                initInternet(response);
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
                GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(url, DiscoverBean.class, new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean response) {
                        adapter.setDiscoverBean(response);
                        lv.onRefreshComplete();
                        initInternet(response);
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

        discoverBtnHeadAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void click(int position, DiscoverBtnHeadAdapter.ViewHolder holder) {
                if (position == 9){
                    Intent intentAll = new Intent(getActivity(),DiscoverBtnActivity.class);
                    startActivity(intentAll);
                }
            }
        });




    }

    private void initInternet(DiscoverBean response){
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {
            if (response.getResult().getCardlist().get(i).getDescription().equals("商品列表")){
                adapter.setId(i);
                adapter.setDiscoverBean(response);
                tvOne.setText(response.getResult().getCardlist().get(i).getTitle());
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("模块列表")){
                if (response.getResult().getCardlist().get(i).getTopblanktype().equals("0")){
                discoverRvHeadAdapter.setId(i);
                discoverRvHeadAdapter.setDiscoverBean(response);
                tvTwo.setText(response.getResult().getCardlist().get(i).getTitle());
                }
                if (response.getResult().getCardlist().get(i).getTopblanktype().equals("2")){
                    discoverRvTwoHeadAdapter.setId(i);
                    discoverRvTwoHeadAdapter.setDiscoverBean(response);
                    tvLove.setText(response.getResult().getCardlist().get(i).getTitle());
                }
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("活动专区")){
                tvHd.setText(response.getResult().getCardlist().get(i).getTitle());
                tvMoreHd.setText(response.getResult().getCardlist().get(i).getRightbtn().getData());
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).resize(126,150).centerCrop().into(ivOne);
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(1).getImageurl()).resize(126,150).centerCrop().into(ivTwo);
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(2).getImageurl()).resize(126,150).centerCrop().into(ivThree);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("单帧小号横栏")){
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(iv);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("限时抢购")){
                rvHorHeadAdapter.setId(i);
                rvHorHeadAdapter.setDiscoverBean(response);
                tvBuy.setText(response.getResult().getCardlist().get(i).getTitle());
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("田字小号专区")){
                tvService.setText(response.getResult().getCardlist().get(i).getTitle());
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(ivServiceOne);
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(1).getImageurl()).into(ivServiceTwo);
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(2).getImageurl()).into(ivServiceThree);
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(3).getImageurl()).into(ivServiceFour);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("业务入口")){
                discoverBtnHeadAdapter.setId(i);
                discoverBtnHeadAdapter.setDiscoverBean(response);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("文字滚动链")){
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getImageurl()).into(ivSmall);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("单帧大号横栏")){
                Picasso.with(mContext).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(ivBtn);
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("焦点图")){
                ArrayList<String>images = new ArrayList<>();
                for (int j = 0; j < response.getResult().getCardlist().get(i).getData().size(); j++) {
                    images.add(response.getResult().getCardlist().get(i).getData().get(j).getImageurl());
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                    banner.setIndicatorGravity(BannerConfig.RIGHT);
                    banner.setImages(images);
                }
            }


        }
    }

    private void getHeadView() {
        //轮播图
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.discover_banner,null);
        banner = new Banner(mContext);
        banner = (Banner) bannerView.findViewById(R.id.discover_banner);

        //btn
        View headBtnView = LayoutInflater.from(mContext).inflate(R.layout.btn_rv_discover_head,null);
        rvBtn = (RecyclerView) headBtnView.findViewById(R.id.rv_btn_discover_head);
        discoverBtnHeadAdapter = new DiscoverBtnHeadAdapter(mContext);
        rvBtn.setAdapter(discoverBtnHeadAdapter);
        GridLayoutManager managerBtn = new GridLayoutManager(mContext,5);
        rvBtn.setLayoutManager(managerBtn);
        ivSmall = (ImageView) headBtnView.findViewById(R.id.iv_small_btn_discover_head);
        ivBtn = (ImageView) headBtnView.findViewById(R.id.iv_btn_discover_head);




        //服务专区
        View headServiceView = LayoutInflater.from(mContext).inflate(R.layout.service_head,null);
        tvService = (TextView) headServiceView.findViewById(R.id.tv_service_head);
        ivServiceOne = (ImageView) headServiceView.findViewById(R.id.iv_service_head_one);
        ivServiceTwo = (ImageView) headServiceView.findViewById(R.id.iv_service_head_two);
        ivServiceThree = (ImageView) headServiceView.findViewById(R.id.iv_service_head_three);
        ivServiceFour = (ImageView) headServiceView.findViewById(R.id.iv_service_head_four);
//        ivServiceFive = (ImageView) headServiceView.findViewById(R.id.iv_service_head_five);
//        ivServiceSix = (ImageView) headServiceView.findViewById(R.id.iv_service_head_six);
        tvBuy = (TextView) headServiceView.findViewById(R.id.tv_buy_head);
        //限时抢购
        View headHorView = LayoutInflater.from(mContext).inflate(R.layout.hor_recycler_head,null);
        rvHor = (RecyclerView) headHorView.findViewById(R.id.rv_hor_head);
        rvHorHeadAdapter = new DiscoverRvHorHeadAdapter(mContext);
        rvHor.setAdapter(rvHorHeadAdapter);
        LinearLayoutManager managerHor = new LinearLayoutManager(mContext);
        rvHor.setLayoutManager(managerHor);
        managerHor.setOrientation(LinearLayoutManager.HORIZONTAL);

        //活动专区----猜你喜欢
        View headViewOne = LayoutInflater.from(mContext).inflate(R.layout.area_head,null);
        tvLove = (TextView) headViewOne.findViewById(R.id.love_area_head);
        iv = (ImageView) headViewOne.findViewById(R.id.iv_area_head);
        ivOne = (ImageView) headViewOne.findViewById(R.id.iv_hd_area_head_one);
        ivTwo = (ImageView) headViewOne.findViewById(R.id.iv_hd_area_head_two);
        ivThree = (ImageView) headViewOne.findViewById(R.id.iv_hd_area_head_three);
        tvMoreHd = (TextView) headViewOne.findViewById(R.id.tv_morehd_area_head);
        tvHd = (TextView) headViewOne.findViewById(R.id.tv_hd_area_head);

        //猜你喜欢
        View headViewRvTwo = LayoutInflater.from(mContext).inflate(R.layout.discover_rv_two_head,null);
        rvTwo = (RecyclerView) headViewRvTwo.findViewById(R.id.rv_discover_two_head);
        tvTwo = (TextView) headViewRvTwo.findViewById(R.id.shoplist_discover_two_head);
        discoverRvTwoHeadAdapter = new DiscoverRvTwoHeadAdapter(mContext);
        rvTwo.setAdapter(discoverRvTwoHeadAdapter);
        GridLayoutManager managerTwo = new GridLayoutManager(mContext,2);
        rvTwo.setLayoutManager(managerTwo);

        //为我推荐头布局
        View headViewRvOne = LayoutInflater.from(mContext).inflate(R.layout.discover_rv_head,null);
        rvOne = (RecyclerView) headViewRvOne.findViewById(R.id.rv_discover_head);
        tvOne = (TextView) headViewRvOne.findViewById(R.id.shoplist_discover_head);
        discoverRvHeadAdapter = new DiscoverRvHeadAdapter(mContext);
        rvOne.setAdapter(discoverRvHeadAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext,2);
        rvOne.setLayoutManager(manager);



        ListView listView = lv.getRefreshableView();
        listView.addHeaderView(bannerView);//轮播图
        listView.addHeaderView(headBtnView);
        listView.addHeaderView(headServiceView);//服务专区
        listView.addHeaderView(headHorView);//限时抢购
        listView.addHeaderView(headViewOne);//活动专区
        listView.addHeaderView(headViewRvTwo);//猜你喜欢
        listView.addHeaderView(headViewRvOne);//为我推荐




    }
}
