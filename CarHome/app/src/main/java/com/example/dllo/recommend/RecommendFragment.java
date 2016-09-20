package com.example.dllo.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.DividerItemDecoration;
import com.example.dllo.carhome.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {
    ArrayList<RecommendBean>recommendBeen = new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_recommend);
    }

    @Override
    protected void initData() {

        int[] pic = {R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4};
        for (int i = 0; i < 10; i++) {
            RecommendBean bean = new RecommendBean();
            String title = "标题" + i;
            String time = "时间" + i;

            bean.setTitle(title);
            bean.setTime(time);
            bean.setPic(pic[i%4]);

            recommendBeen.add(bean);
        }
        RecommendAdapter adapter = new RecommendAdapter(mContext);
        for (int i = 0; i < recommendBeen.size(); i++) {
            Log.d("RecommendFragment", recommendBeen.get(i).getTitle());
        }
        adapter.setArrayList(recommendBeen);



        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        rv.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));
        rv.setAdapter(adapter);
    }
}
