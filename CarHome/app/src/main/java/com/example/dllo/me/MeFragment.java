package com.example.dllo.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;

/**
 * Created by dllo on 16/9/19.
 */
public class MeFragment extends BaseFragment {

    private ImageButton btnSet;

    @Override
    protected int setLayout() {
        return R.layout.me_fragment;
    }

    @Override
    protected void initView() {
        btnSet = bindView(R.id.btn_me_set);
    }

    @Override
    protected void initData() {
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SetActivity.class);
                startActivity(intent);
            }
        });
    }
}
