package com.example.dllo.recommend.more;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.carhome.BaseAty;
import com.example.dllo.carhome.R;
import com.example.dllo.intentnet.GsonRequest;
import com.example.dllo.intentnet.VolleySingleton;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by dllo on 16/10/9.
 */
public class MoreActivity extends BaseAty {

    String MORE_ACTIVI_URL = "http://news.app.autohome.com.cn/shouye_v7.0.0/mobile/metadata.ashx?a=2&pm=2&v=7.0.7&types=newstype%7C636002832282225908%2Cvideotype%7C636002832282225908";
    private Button button;
    private RecyclerView rc;
    private ItemTouchHelper mHelper;
    private MoreReAdaper moreAdapter;
    private ImageView mIvBack;

    @Override
    protected int setLayout() {
        return R.layout.moreactvity;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.more_activity_img);
        rc = bindView(R.id.more_activity_recylev);


    }

    @Override
    protected void initData() {

       initRequestInternet();
        rc.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        rc.addItemDecoration(new DividerItemDecoration(this,RecyclerView.HORIZONTAL));






    }

    private void initRequestInternet() {
        GsonRequest<MoreBean> gsonRequest = new GsonRequest<MoreBean>(MORE_ACTIVI_URL, MoreBean.class, new Response.Listener<MoreBean>() {
            @Override
            public void onResponse(final MoreBean response) {
                moreAdapter = new MoreReAdaper(MoreActivity.this);
                moreAdapter.setBean(response);
                rc.setAdapter(moreAdapter);
                GridLayoutManager manager = new GridLayoutManager(MoreActivity.this,3);
                rc.setLayoutManager(manager);
                ItemTouchHelper.Callback callback = createCallback();
                mHelper = new ItemTouchHelper(callback);
                mHelper.attachToRecyclerView(rc);
               moreAdapter.setOnClickLisenerRcycleMoreActy(new OnClickLisenerRcycleMoreActy() {
                   @Override
                   public void onClick(int position, MoreReAdaper.ViewHolder viewHolder) {

                       EventBean eventBean = new EventBean();
                       eventBean.setContent(response.getResult().getMetalist().get(0).getList().get(viewHolder.getLayoutPosition()).getName());
                       eventBean.setNum(position);
                       EventBus.getDefault().post(eventBean);
                       MoreActivity.this.finish();



                   }
               });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    private ItemTouchHelper.Callback createCallback() {
        return new ItemTouchHelper.Callback() {
            //决定RecyclerView的行布局支持哪种手势
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //两个参数
                //参数一 决定行布局支持哪种拖拉的手势
                //参数二 决定行布局支持哪种滑动的手势
                return ItemTouchHelper.Callback.makeMovementFlags(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
                        0
                );
            }
            //作用:移动了行布局之后会回调该方法
            //参数一
            //参数二 移动的行布局对应的ViewHolder
            //参数三 移动到的位置所对应的ViewHolder

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                moreAdapter.move(from, to);
                return false;
            }
            // 滑动杭布局事会回调
            //参数一 滑动的行布局所对应的ViewHolder
            //参数二 滑动的方向

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
////                activityAdapter.add(position);

            }

            //选择性覆写 返回值决定是否启用长按拖动功能
            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
            }

//            //选择性覆写 返回值决定是否启用滑动功能
//            @Override
//            public boolean isItemViewSwipeEnabled() {
//                return super.isItemViewSwipeEnabled();
//            }
// 当长按选中item的时候(拖动开始的时候)调用
@Override
public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
        viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
    }
    super.onSelectedChanged(viewHolder, actionState);
}

            // 当手指松开的时候(拖动完成的时候调用
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        };

    }


}
