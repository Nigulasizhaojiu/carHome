package com.example.dllo.me;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/10/13.
 */
public class PushActivity extends BaseAty{

    private Switch sysMessage;
    private TextView tvSetDate;
    private LinearLayout ll;
    private SetPushReciever setPushReciever;
    private SharedPreferences.Editor spEt;
    private int startHour;
    private int endHour;
    private SharedPreferences sp;
    private NumberPicker timeStart;
    private NumberPicker timeEnd;
    private Button btnCancel;
    private Button btnDetermine;

    @Override
    protected int setLayout() {
        return R.layout.push_activity;
    }

    @Override
    protected void initView() {
        sysMessage = bindView(R.id.system_message);
        ll = bindView(R.id.ll_set_date);
        tvSetDate = bindView(R.id.tv_set_date);

    }

    @Override
    protected void initData() {
        //接收推送时间
        setPushReciever = new SetPushReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("setPushTime");
        registerReceiver(setPushReciever,filter);

        initSwitchBtn();
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopWinPushDate();
            }
        });


    }

    private void initSwitchBtn() {
        sp = getSharedPreferences("lauch",MODE_PRIVATE);
        spEt = sp.edit();

        Boolean isSysMsg = sp.getBoolean("isSysMsg",true);
        startHour = sp.getInt("startNum",8);
        endHour = sp.getInt("endNum",22);

        tvSetDate.setText("每日 " + startHour + ":00 - " + endHour + ":00" );

        if (isSysMsg == true){
            sysMessage.setChecked(true);
        }else {
            sysMessage.setChecked(false);
        }
        sysMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spEt.putBoolean("isSysMsg", true);
                    spEt.commit();
                    JPushInterface.resumePush(getApplicationContext());
                } else {
                    spEt.putBoolean("isSysMsg", false);
                    spEt.commit();
                    JPushInterface.stopPush(getApplicationContext());
                }
            }
        });
    }

    private void initPopWinPushDate() {
        View pushView = LayoutInflater.from(this).inflate(R.layout.pop_win_push_date, null);
        btnCancel = (Button) pushView.findViewById(R.id.btn_pop_win_cannel);
        btnDetermine = (Button) pushView.findViewById(R.id.btn_pop_win_determine);
        timeStart = (NumberPicker) pushView.findViewById(R.id.time_picker_start);
        timeEnd = (NumberPicker) pushView.findViewById(R.id.time_picker_end);

        String[] dates = {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00"
                ,"12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
        timeStart.setDisplayedValues(dates);
        timeStart.setMinValue(0);
        timeStart.setMaxValue(dates.length - 1);

        timeEnd.setDisplayedValues(dates);
        timeEnd.setMinValue(0);
        timeEnd.setMaxValue(dates.length - 1);

        startHour = sp.getInt("startNum", 8);
        endHour = sp.getInt("endNum", 22);

        timeStart.setValue(startHour);
        timeEnd.setValue(endHour);


        final PopupWindow popupWindow = new PopupWindow(pushView, ViewGroup.LayoutParams.MATCH_PARENT, 400,true);
        //设置PopupWindow的弹出和消失效果
        popupWindow.setAnimationStyle(R.style.push_date_anim);
        popupWindow.showAtLocation(ll, Gravity.BOTTOM, 0, 0);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNumPicker();
                popupWindow.dismiss();
            }
        });
    }

    private void initNumPicker() {

        timeStart.setValue(timeStart.getValue());
        timeEnd.setValue(timeEnd.getValue());

        if (timeStart.getValue() >= timeEnd.getValue()) {
            Toast.makeText(this, "输入格式错误", Toast.LENGTH_SHORT).show();
        } else {
            spEt.putInt("startNum", timeStart.getValue());
            spEt.putInt("endNum", timeEnd.getValue());
            spEt.commit();

            Intent intent = new Intent("setPushTime");
            intent.putExtra("startTimeHour", "" + timeStart.getValue());
            intent.putExtra("endTimeHour", "" + timeEnd.getValue());
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(setPushReciever);
    }



    private class SetPushReciever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String startTimeHour = intent.getStringExtra("startTimeHour");
            String endTimeHour = intent.getStringExtra("endTimeHour");

            tvSetDate.setText("每日 "+ startTimeHour + ":00 - "+ endTimeHour + ":00" );
        }
    }
}