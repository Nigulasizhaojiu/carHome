package com.example.dllo.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.recommend.other.OtherFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/21.
 */
public class RecommendAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> forumFragments = new ArrayList<>();
    ArrayList<String>title = new ArrayList<>();

    public RecommendAdapter(FragmentManager fm, ArrayList<Fragment> forumFragments) {
        super(fm);
        this.forumFragments = forumFragments;
        String[] strings = new String[]{"推荐","优创+","说客","视频","快报","行情","新闻","评测","导购","用车","技术","文化","改装"};
        for (int i = 0; i < strings.length; i++) {
            title.add(strings[i]);
        }

    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }


    @Override
    public Fragment getItem(int position) {
        if (position >= 6){
            OtherFragment otherFragment = new OtherFragment();
            return otherFragment.newInstance(position);
        } else {
            return forumFragments.get(position);
        }
    }

    @Override
    public int getCount() {
        return 13;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
