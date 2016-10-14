package com.example.dllo.find;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/27.
 */
public class FindAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment>finds = new ArrayList<>();
    ArrayList<String>title = new ArrayList<>();

    public FindAdapter(FragmentManager fm, ArrayList<Fragment> finds) {
        super(fm);
        this.finds = finds;
        title.add("新车");
        title.add("二手车");
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return finds.get(position);
    }

    @Override
    public int getCount() {
        return finds == null ? 0 : finds.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
