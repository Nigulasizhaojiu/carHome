package com.example.dllo.carhome;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.dllo.discover.DiscoverFragment;
import com.example.dllo.find.FindFragment;
import com.example.dllo.forum.ForumFragment;
import com.example.dllo.me.MeFragment;
import com.example.dllo.recommend.RecommendFragment;

public class MainActivity extends BaseAty implements View.OnClickListener{


    private Button recommend;
    private Button forum;
    private Button find;
    private Button discover;
    private Button me;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recommend = bindView(R.id.radio_btn_recommend);
        forum = bindView(R.id.radio_btn_forum);
        find = bindView(R.id.radio_btn_find);
        discover = bindView(R.id.radio_btn_discover);
        me = bindView(R.id.radio_btn_me);
        recommend.setOnClickListener(this);
        forum.setOnClickListener(this);
        find.setOnClickListener(this);
        discover.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fl,new RecommendFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        recommend.setBackgroundResource(R.mipmap.nav_icon_article_f);
        forum.setBackgroundResource(R.mipmap.nav_icon_forum_f);
        find.setBackgroundResource(R.mipmap.nav_icon_findcar_f);
        discover.setBackgroundResource(R.mipmap.nav_icon_sale_f);
        me.setBackgroundResource(R.mipmap.nav_icon_my_f);
        switch (view.getId()){
            case R.id.radio_btn_recommend:
                fragmentTransaction.replace(R.id.main_fl,new RecommendFragment());
                recommend.setBackgroundResource(R.mipmap.nav_icon_article_p);
                break;
            case R.id.radio_btn_forum:
                fragmentTransaction.replace(R.id.main_fl,new ForumFragment());
                forum.setBackgroundResource(R.mipmap.nav_icon_forum_p);
                break;
            case R.id.radio_btn_find:
                fragmentTransaction.replace(R.id.main_fl,new FindFragment());
                find.setBackgroundResource(R.mipmap.nav_icon_findcar_p);
                break;
            case R.id.radio_btn_discover:
                fragmentTransaction.replace(R.id.main_fl,new DiscoverFragment());
                discover.setBackgroundResource(R.mipmap.nav_icon_sale_p);
                break;
            case R.id.radio_btn_me:
                fragmentTransaction.replace(R.id.main_fl,new MeFragment());
                me.setBackgroundResource(R.mipmap.nav_icon_my_p);
                break;
        }
        fragmentTransaction.commit();

    }
}
