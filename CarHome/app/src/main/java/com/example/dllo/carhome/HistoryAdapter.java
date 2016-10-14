package com.example.dllo.carhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/12.
 */
public class HistoryAdapter extends BaseAdapter {
    ArrayList<HistoryBean>historyBean = new ArrayList<>();
    Context context;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    public void setHistoryBean(ArrayList<HistoryBean> historyBean) {
        this.historyBean = historyBean;
    }



    @Override
    public int getCount() {
        return historyBean == null ? 0 : historyBean.size();
    }

    @Override
    public Object getItem(int i) {
        return historyBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.history_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv.setText(historyBean.get(i).getName());
        return view;
    }

    private class ViewHolder {

        private final TextView tv;

        public ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_history_list);
        }
    }
}
