package com.example.dllo.forum.alls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.carhome.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/21.
 */
public class AllsAdapter extends BaseAdapter {
    ArrayList<AllsBean>allsBeanArrayList = new ArrayList<>();
    Context context;

    public AllsAdapter(Context context) {
        this.context = context;
    }

    public void setAllsBeanArrayList(ArrayList<AllsBean> allsBeanArrayList) {
        this.allsBeanArrayList = allsBeanArrayList;
    }

    @Override
    public int getCount() {
        return allsBeanArrayList == null ? 0 : allsBeanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return allsBeanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.alls_list,null);
            viewholder = new Viewholder(view);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.allsTitle.setText(allsBeanArrayList.get(i).getAllsTitle());

        return view;
    }

    private class Viewholder {

        private final TextView allsTitle;

        public Viewholder(View convertView) {
            allsTitle = (TextView) convertView.findViewById(R.id.alls_title);
        }
    }
}
