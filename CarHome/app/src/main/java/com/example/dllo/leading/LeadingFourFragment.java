package com.example.dllo.leading;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.carhome.BaseFragment;
import com.example.dllo.carhome.R;
import com.example.dllo.carhome.ShowActivity;


/**
 * Created by dllo on 16/9/19.
 */
public class LeadingFourFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton experience;

    @Override
    protected int setLayout() {
        return R.layout.four_leading;
    }

    @Override
    protected void initView() {
        experience = bindView(R.id.immediate_experience,getView());
        experience.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        SharedPreferences sp = mContext.getSharedPreferences("mengxiangdong", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEt = sp.edit();
        spEt.putBoolean("mxd",true);
        spEt.commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.immediate_experience:
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
