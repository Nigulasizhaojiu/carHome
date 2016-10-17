package com.example.dllo.forum.selected;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;
import com.example.dllo.carhome.WebViewActivity;
import com.example.dllo.forum.alls.AllsActivity;
import com.example.dllo.forum.allselected.AllSelectedActivity;
import com.example.dllo.forum.twentyfour.TwentyFourActivity;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class SelectedFragment extends BaseFragment implements View.OnClickListener{
    private PullToRefreshListView lv;
//    String url = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c0-p1-s30.json";
    String url = "http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex=1&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121.550943";
    private ImageButton allSelected;
    private Button btnXfdcm,btnMrj,btnGdzd,btnMytd,btnJtxx,btnMzxc,btnLthrg,btnGzyl,btnRt;
    private View headView;
    private int num = 0;

    @Override
    protected int setLayout() {
        return R.layout.selected_fragment;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_selected);
    }


    @Override
    protected void initData() {

        lv.setMode(PullToRefreshBase.Mode.BOTH);
        final SelectedAdapter adapter = new SelectedAdapter(mContext);
        lv.setAdapter(adapter);


        //获取网络数据
        GsonRequest<SelectedBean> gsonRequest = new GsonRequest<SelectedBean>(url, SelectedBean.class, new Response.Listener<SelectedBean>() {
            @Override
            public void onResponse(SelectedBean response) {
                adapter.setSelectedBean(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

        //头布局
        headView = LayoutInflater.from(mContext).inflate(R.layout.selected_head,null);
        ListView listView = lv.getRefreshableView();
        listView.addHeaderView(headView);

        allSelected = (ImageButton) headView.findViewById(R.id.btn_select_all);
        allSelected.setOnClickListener(this);
        btnRt = bindView(R.id.btn_select_rt,headView);
        btnRt.setOnClickListener(this);
        btnXfdcm = bindView(R.id.btn_select_xfdcm,headView);
        btnXfdcm.setOnClickListener(this);
        btnMrj = bindView(R.id.btn_select_mrj,headView);
        btnMrj.setOnClickListener(this);
        btnGdzd = bindView(R.id.btn_select_gdzd,headView);
        btnGdzd.setOnClickListener(this);
        btnMytd = bindView(R.id.btn_select_mytd,headView);
        btnMytd.setOnClickListener(this);
        btnJtxx = bindView(R.id.btn_select_jtxx,headView);
        btnJtxx.setOnClickListener(this);
        btnMzxc = bindView(R.id.btn_select_mzxc,headView);
        btnMzxc.setOnClickListener(this);
        btnLthrg = bindView(R.id.btn_select_lthrg,headView);
        btnLthrg.setOnClickListener(this);
        btnGzyl = bindView(R.id.btn_select_gzyl,headView);
        btnGzyl.setOnClickListener(this);
        setBtnBackGround();

        //上拉加载下拉刷新
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                GsonRequest<SelectedBean> gsonRequest = new GsonRequest<SelectedBean>(url, SelectedBean.class, new Response.Listener<SelectedBean>() {
                    @Override
                    public void onResponse(SelectedBean response) {
                        adapter.setSelectedBean(response);
                        setBtnBackGround();
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

                num = num + 1;
                String urls ="http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex="+ num+"&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121.550943";
                // Log.d("试试", url);
                //   forumListV.onRefreshComplete();

                GsonRequest<SelectedBean> gsonRequest  = new GsonRequest<SelectedBean>(urls, SelectedBean.class,
                        new Response.Listener<SelectedBean>() {
                            @Override
                            public void onResponse(SelectedBean response) {
                                adapter.setSelectedBean1(response);

                                lv.onRefreshComplete();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);




            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                int urlId = 0;
                SelectedBean selectedBean = (SelectedBean) adapterView.getItemAtPosition(i);
                urlId = selectedBean.getResult().getList().get(i-2).getTopicid();
                String urls = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"
                        + urlId + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                intent.putExtra("urlWv",urls);
                getActivity().startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_select_all:
                Intent intent = new Intent(getActivity(), AllsActivity.class);
                startActivity(intent);
                Log.d("SelectedFragment", "哈哈");
                break;
            case R.id.btn_select_rt:
                Intent intent1 = new Intent(getActivity(), TwentyFourActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_select_xfdcm:
                Intent intentXfdcm = new Intent(getActivity(), AllSelectedActivity.class);
                intentXfdcm.putExtra("position",0);
                startActivity(intentXfdcm);
                Log.d("SelectedFragment", "哈哈哈哈啊哈哈哈");
                break;
            case R.id.btn_select_mrj:
                Intent intentMrj = new Intent(getActivity(),AllSelectedActivity.class);
                intentMrj.putExtra("position",1);
                startActivity(intentMrj);
                break;
            case R.id.btn_select_gdzd:
                Intent intentGdzd = new Intent(getActivity(),AllSelectedActivity.class);
                intentGdzd.putExtra("position",6);
                startActivity(intentGdzd);
                break;
            case R.id.btn_select_mytd:
                Intent intentMytd = new Intent(getActivity(),AllSelectedActivity.class);
                intentMytd.putExtra("position",22);
                startActivity(intentMytd);
                break;
            case R.id.btn_select_jtxx:
                Intent intentJtxx = new Intent(getActivity(),AllSelectedActivity.class);
                intentJtxx.putExtra("position",4);
                startActivity(intentJtxx);
                break;
            case R.id.btn_select_mzxc:
                Intent intentMzxc = new Intent(getActivity(),AllSelectedActivity.class);
                intentMzxc.putExtra("position",13);
                startActivity(intentMzxc);
                break;
            case R.id.btn_select_lthrg:
                Intent intentLthrg = new Intent(getActivity(),AllSelectedActivity.class);
                intentLthrg.putExtra("position",2);
                startActivity(intentLthrg);
                break;
            case R.id.btn_select_gzyl:
                Intent intentGzyl = new Intent(getActivity(),AllSelectedActivity.class);
                intentGzyl.putExtra("position",17);
                startActivity(intentGzyl);
                break;
        }

    }

    //btn的随机背景和字体颜色
    private void setBtnBackGround(){
        ArrayList<Button>buttons = new ArrayList<>();
        buttons.add(btnXfdcm);
        buttons.add(btnMrj);
        buttons.add(btnGdzd);
        buttons.add(btnMytd);
        buttons.add(btnJtxx);
        buttons.add(btnMzxc);
        buttons.add(btnLthrg);
        buttons.add(btnGzyl);
        ArrayList<Integer>backgrounds = new ArrayList<>();
        backgrounds.add(R.drawable.blue_btn_bg);
        backgrounds.add(R.drawable.orange_btn_bg);
        ArrayList<Integer>inColors = new ArrayList<>();
        inColors.add(getResources().getColor(R.color.blue));
        inColors.add(getResources().getColor(R.color.orange));
        for (int i = 0; i < buttons.size(); i++) {
            int color = (int)(Math.random()*2);
            buttons.get(i).setBackgroundResource(backgrounds.get(color));
            buttons.get(i).setTextColor(inColors.get(color));
        }

    }

}
