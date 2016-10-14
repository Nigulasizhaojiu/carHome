package com.example.dllo.carhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/11.
 */
public class FindatyAdapter extends BaseAdapter{
    FindatyBean findatyBean;
    Context context;

    public FindatyAdapter(Context context) {
        this.context = context;
    }

    public void setFindatyBean(FindatyBean findatyBean) {
        this.findatyBean = findatyBean;
    }

    @Override
    public int getCount() {
        return findatyBean == null ? 0 : findatyBean.getResult().getWordlist().size();
    }

    @Override
    public Object getItem(int i) {
        return findatyBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.find_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.car.setText(findatyBean.getResult().getWordlist().get(i).getName());
        return view;
    }

    private class ViewHolder {
        private final TextView car;
        public ViewHolder(View view) {
            car = (TextView) view.findViewById(R.id.tv_find_list);
        }
    }
}
