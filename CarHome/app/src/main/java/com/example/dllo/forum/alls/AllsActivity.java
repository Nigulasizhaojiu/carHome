package com.example.dllo.forum.alls;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;
import com.example.dllo.forum.allselected.AllSelectedActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/21.
 */
public class AllsActivity extends BaseAty{
    ListView lvAlls;
    ArrayList<AllsBean>arrayList =new ArrayList<>();
    @Override
    protected int setLayout() {
        return R.layout.alls_activity;
    }

    @Override
    protected void initView() {
        lvAlls = bindView(R.id.alls_lv);

        lvAlls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AllsActivity.this, AllSelectedActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        AllsAdapter allsAdapter = new AllsAdapter(this);
        allsAdapter.setAllsBeanArrayList(arrayList);
        lvAlls.setAdapter(allsAdapter);
        String[] alls = this.getResources().getStringArray(R.array.alls);
        for (int i = 0; i < alls.length; i++) {
            AllsBean bean = new AllsBean();
            bean.setAllsTitle(alls[i]);
            arrayList.add(bean);
        }







    }
}
