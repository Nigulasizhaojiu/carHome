package com.example.dllo.forum;

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
import com.example.dllo.forum.forums.ForumsFragment;
import com.example.dllo.forum.selected.SelectedFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class ForumFragment extends BaseFragment {

    private ViewPager vpForum;
    private TabLayout tbForum;
    private ImageView ivFind;

    @Override
    protected int setLayout() {
        return R.layout.forum_fragment;
    }

    @Override
    protected void initView() {
        vpForum = bindView(R.id.vp_forum);
        tbForum = bindView(R.id.tb_forum);
        ivFind = bindView(R.id.iv_find_forum);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectedFragment());
        fragments.add(new ForumsFragment());
        ForumAdapter adapter = new ForumAdapter(getChildFragmentManager(),fragments);
        vpForum.setAdapter(adapter);
        tbForum.setupWithViewPager(vpForum);
        tbForum.setSelectedTabIndicatorColor(Color.BLACK);
        tbForum.setTabTextColors(Color.GRAY,Color.BLACK);
        ivFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });
    }
}
