package com.example.dllo.carhome;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.discover.DiscoverFragment;
import com.example.dllo.find.FindFragment;
import com.example.dllo.forum.ForumFragment;
import com.example.dllo.me.MeFragment;
import com.example.dllo.recommend.RecommendFragment;

/**
 * Created by dllo on 16/10/11.
 */
public class MainActivity extends BaseAty implements View.OnClickListener{
    private RadioButton rBtnRecommend;
    private RadioButton rBtnForum;
    private RadioButton rBtnFindCar;
    private RadioButton rBtnDiscover;
    private RadioButton rBtnPersonal;
    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rBtnRecommend = bindView(R.id.radio_btn_recommend);
        rBtnForum = bindView(R.id.radio_btn_forum);
        rBtnFindCar = bindView(R.id.radio_btn_find);
        rBtnDiscover = bindView(R.id.radio_btn_discover);
        rBtnPersonal = bindView(R.id.radio_btn_me);

    }

    @Override
    protected void initData() {
        rBtnRecommend.setOnClickListener(this);
        rBtnForum.setOnClickListener(this);
        rBtnFindCar.setOnClickListener(this);
        rBtnDiscover.setOnClickListener(this);
        rBtnPersonal.setOnClickListener(this);

        rBtnRecommend.setChecked(true);

        // 默认显示的碎片
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fl, new RecommendFragment());
        transaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()){
            case R.id.radio_btn_recommend:
                transaction.replace(R.id.main_fl, new RecommendFragment());
                rBtnRecommend.setBackgroundResource(R.mipmap.nav_icon_article_p);
                rBtnForum.setBackgroundResource(R.mipmap.nav_icon_forum_f);
                rBtnFindCar.setBackgroundResource(R.mipmap.nav_icon_findcar_f);
                rBtnDiscover.setBackgroundResource(R.mipmap.nav_icon_sale_f);
                rBtnPersonal.setBackgroundResource(R.mipmap.nav_icon_my_f);
                break;
            case R.id.radio_btn_forum:
                transaction.replace(R.id.main_fl, new ForumFragment());
                rBtnRecommend.setBackgroundResource(R.mipmap.nav_icon_article_f);
                rBtnForum.setBackgroundResource(R.mipmap.nav_icon_forum_p);
                rBtnFindCar.setBackgroundResource(R.mipmap.nav_icon_findcar_f);
                rBtnDiscover.setBackgroundResource(R.mipmap.nav_icon_sale_f);
                rBtnPersonal.setBackgroundResource(R.mipmap.nav_icon_my_f);
                break;
            case R.id.radio_btn_find:
                transaction.replace(R.id.main_fl, new FindFragment());
                rBtnRecommend.setBackgroundResource(R.mipmap.nav_icon_article_f);
                rBtnForum.setBackgroundResource(R.mipmap.nav_icon_forum_f);
                rBtnFindCar.setBackgroundResource(R.mipmap.nav_icon_findcar_p);
                rBtnDiscover.setBackgroundResource(R.mipmap.nav_icon_sale_f);
                rBtnPersonal.setBackgroundResource(R.mipmap.nav_icon_my_f);
                break;
            case R.id.radio_btn_discover:
                transaction.replace(R.id.main_fl, new DiscoverFragment());
                rBtnRecommend.setBackgroundResource(R.mipmap.nav_icon_article_f);
                rBtnForum.setBackgroundResource(R.mipmap.nav_icon_forum_f);
                rBtnFindCar.setBackgroundResource(R.mipmap.nav_icon_findcar_f);
                rBtnDiscover.setBackgroundResource(R.mipmap.nav_icon_sale_p);
                rBtnPersonal.setBackgroundResource(R.mipmap.nav_icon_my_f);
                break;
            case R.id.radio_btn_me:
                transaction.replace(R.id.main_fl, new MeFragment());
                rBtnRecommend.setBackgroundResource(R.mipmap.nav_icon_article_f);
                rBtnForum.setBackgroundResource(R.mipmap.nav_icon_forum_f);
                rBtnFindCar.setBackgroundResource(R.mipmap.nav_icon_findcar_f);
                rBtnDiscover.setBackgroundResource(R.mipmap.nav_icon_sale_f);
                rBtnPersonal.setBackgroundResource(R.mipmap.nav_icon_my_p);
                break;
        }
        transaction.commit();
    }
}
