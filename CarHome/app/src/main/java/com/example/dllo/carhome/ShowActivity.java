package com.example.dllo.carhome;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/11.
 */
public class ShowActivity extends BaseAty {
    private TextView mBtnSkip;
//    private ImageView mImage;
//    private CountDownTimer mTimer;

    @Override
    protected int setLayout() {
        return R.layout.show_activity;
    }

    @Override
    protected void initView() {
        mBtnSkip = bindView(R.id.count_down);
//        mImage = bindView(R.id.count_down_image);
    }

    @Override
    protected void initData() {


        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mTimer.cancel();
                Intent intent = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        sendInterent();
    }

//    private void sendInterent() {
//        GsonRequest<WelcomeBean> gsonRequest = new GsonRequest<WelcomeBean>(URLValues.WELCOME_URL,
//                WelcomeBean.class,
//                new Response.Listener<WelcomeBean>() {
//                    @Override
//                    public void onResponse(WelcomeBean response) {
//
////                        if (response.getResult().getAd().getImgad().getImgurl().equals("")) {
//                        mImage.setBackgroundColor(Color.WHITE);
////                        } else {
////                            ImageLoader.getInstance().displayImage(response.getResult().getAd().getImgad().getImgurl(), mImage);
////                        }
//                        initCountDown(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(WelcomeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//        VolleySingleton.getInstance().addRequest(gsonRequest);
//    }

    // 这个方法就是 倒计时反复执行的方法
//    private void initCountDown() {
//            // 倒计时结束后执行的方法, 在这里执行跳转
//            // response.getResult().getAd().getShowtime()
//            mTimer = new CountDownTimer(3 * 1000, 1000) {
//                @Override
//                public void onTick(long l) {
//
//                }
//
//                // 倒计时结束后执行的方法, 在这里执行跳转
//                @Override
//                public void onFinish() {
//                    Intent intent = new Intent(ShowActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }.start();
//        }
//}
}