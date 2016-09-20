package com.example.dllo.leading;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;
import com.example.dllo.carhome.ShowActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class LeadingActivity extends BaseAty {

    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.leading_activity;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_leading);
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("mengxiangdong",MODE_PRIVATE);
        SharedPreferences.Editor spEt = sp.edit();
        spEt.commit();
        Boolean isFirst = sp.getBoolean("mxd",false);
        if (isFirst){
            Intent intent = new Intent(LeadingActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }

        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(new LeadingOneFragment());
        fragments.add(new LeadingTwoFragment());
        fragments.add(new LeadingThreeFragment());
        fragments.add(new LeadingFourFragment());
        LeadingAdapter adapter = new LeadingAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(adapter);
    }
}
