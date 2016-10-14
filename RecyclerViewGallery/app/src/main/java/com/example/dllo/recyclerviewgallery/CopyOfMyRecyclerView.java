package com.example.dllo.recyclerviewgallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 16/10/13.
 */
public class CopyOfMyRecyclerView extends RecyclerView {


    private View mCurrentView;

    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener)
    {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener
    {
        void onChange(View view, int position);
    }

    public CopyOfMyRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
//        this.setOnScrollListener();
        this.setOnScrollListener(this);
    }

    private void setOnScrollListener(CopyOfMyRecyclerView copyOfMyRecyclerView) {
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        mCurrentView = getChildAt(0);

        if (mItemScrollChangeListener != null)
        {
            mItemScrollChangeListener.onChange(mCurrentView, getChildPosition(mCurrentView));
        }
    }


    @Override
    public void onScrollStateChanged(int arg0)
    {
    }

    /**
     *
     * 滚动时，判断当前第一个View是否发生变化，发生才回调
     */
    @Override
    public void onScrolled(int arg0, int arg1)
    {
        View newView = getChildAt(0);

        if (mItemScrollChangeListener != null)
        {
            if (newView != null && newView != mCurrentView)
            {
                mCurrentView = newView ;
                mItemScrollChangeListener.onChange(mCurrentView,
                        getChildAdapterPosition(mCurrentView));

            }
        }

    }

}