package com.example.dllo.recommend;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.FindActivity;
import com.example.dllo.carhome.R;
import com.example.dllo.recommend.fastnews.FastNewsFragment;
import com.example.dllo.recommend.goodcreate.GoodCreatedFragment;
import com.example.dllo.recommend.market.MarketFragment;
import com.example.dllo.recommend.more.MoreActivity;
import com.example.dllo.recommend.other.OtherFragment;
import com.example.dllo.recommend.recommends.RecommendsFragment;
import com.example.dllo.recommend.say.SayFragment;
import com.example.dllo.recommend.tv.TvFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {


    private ViewPager vp;
    private TabLayout tb;
    private ImageView iv;
    private ImageView ivFind;

    @Override
    protected int setLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_recommend);
        tb = bindView(R.id.tb_recommend);
        iv = bindView(R.id.iv_recommend_more);
        ivFind = bindView(R.id.iv_find_recommend);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(new RecommendsFragment());
        fragments.add(new GoodCreatedFragment());
        fragments.add(new SayFragment());
        fragments.add(new TvFragment());
        fragments.add(new FastNewsFragment());
        fragments.add(new MarketFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        RecommendAdapter adapter = new RecommendAdapter(getChildFragmentManager(),fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
        tb.setSelectedTabIndicatorColor(Color.BLACK);
        tb.setTabTextColors(Color.GRAY,Color.BLACK);

        ivFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MoreActivity.class);
                startActivity(i);
            }
        });
    }
}
