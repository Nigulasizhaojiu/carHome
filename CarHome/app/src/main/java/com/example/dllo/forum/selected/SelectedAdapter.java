package com.example.dllo.forum.selected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.carhome.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/20.
 */
public class SelectedAdapter extends BaseAdapter {
    SelectedBean selectedBean;
    Context context;


    public SelectedAdapter(Context context) {
        this.context = context;

    }

    public void setSelectedBean(SelectedBean selectedBean) {
        this.selectedBean = selectedBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selectedBean == null ? 0 : selectedBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return selectedBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.selected_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(selectedBean.getResult().getList().get(i).getTitle());
        viewHolder.forum.setText(selectedBean.getResult().getList().get(i).getBbsname());
        viewHolder.count.setText(selectedBean.getResult().getList().get(i).getReplycounts()+"回帖");
        Picasso.with(context).load(selectedBean.getResult().getList().get(i).getSmallpic()).into(viewHolder.iv);

        return view;
    }

    public void setSelectedBean1(SelectedBean selectedBean1) {
        selectedBean.getResult().getList().addAll(selectedBean1.getResult().getList());
        notifyDataSetChanged();
    }

    private class ViewHolder {

        private final TextView title;
        private final ImageView iv;
        private final TextView count;
        private final TextView forum;

        public ViewHolder(View convertView) {
            forum = (TextView) convertView.findViewById(R.id.forum_selected);
            count = (TextView) convertView.findViewById(R.id.count_selected);
            iv = (ImageView) convertView.findViewById(R.id.iv_selected);
            title = (TextView) convertView.findViewById(R.id.title_selected);
        }
    }
}
