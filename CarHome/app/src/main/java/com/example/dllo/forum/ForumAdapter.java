package com.example.dllo.forum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class ForumAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> forumFragments = new ArrayList<>();
    ArrayList<String>title = new ArrayList<>();
    public ForumAdapter(FragmentManager fm,ArrayList<Fragment>forumFragments) {
        super(fm);
        this.forumFragments = forumFragments;
        title.add("精选");
        title.add("论坛");
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return forumFragments.get(position);
    }

    @Override
    public int getCount() {
        return forumFragments == null ? 0 : forumFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
