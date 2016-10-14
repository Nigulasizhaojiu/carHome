package com.example.dllo.recommend;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */
public class ReUrlBean {
    private static final ArrayList<String> URLArrayList = new ArrayList<>();

    public static final ArrayList<String> getUrls() {
        URLArrayList.add(ReUrl.RECOMMEND_URL);
        URLArrayList.add(ReUrl.GOOD_CREATE_URL);
        URLArrayList.add(ReUrl.SAY_URL);
        URLArrayList.add(ReUrl.TV_URL);
        URLArrayList.add(ReUrl.FAST_NEWS_URL);
        URLArrayList.add(ReUrl.MARKET_URL);
        URLArrayList.add(ReUrl.NEWS_URL);
        URLArrayList.add(ReUrl.TEST_URL);
        URLArrayList.add(ReUrl.BUY_URL);
        URLArrayList.add(ReUrl.USE_CAR_URL);
        URLArrayList.add(ReUrl.TECENOLOGY_URL);
        URLArrayList.add(ReUrl.CULTURE_URL);
        URLArrayList.add(ReUrl.CHANGE_URL);
        return URLArrayList;
    }
}
