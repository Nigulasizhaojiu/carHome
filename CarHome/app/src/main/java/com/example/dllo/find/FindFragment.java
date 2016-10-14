package com.example.dllo.find;

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
import com.example.dllo.find.newcar.NewFragment;
import com.example.dllo.find.oldcar.OldFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class FindFragment extends BaseFragment{


    private ViewPager findVp;
    private TabLayout findTb;
    private ImageView ivFind;

    @Override
    protected int setLayout() {
        return R.layout.find_fragment;
    }

    @Override
    protected void initView() {
        findTb = bindView(R.id.tb_find);
        findVp = bindView(R.id.vp_find);
        ivFind = bindView(R.id.iv_find_find);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(new NewFragment());
        fragments.add(new OldFragment());
        FindAdapter adapter = new FindAdapter(getChildFragmentManager(),fragments);
        findVp.setAdapter(adapter);
        findTb.setupWithViewPager(findVp);
        findTb.setSelectedTabIndicatorColor(Color.BLACK);
        findTb.setTabTextColors(Color.GRAY,Color.BLACK);
        ivFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });

    }
}
