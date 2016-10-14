package com.example.dllo.recommend.say;

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
 * Created by dllo on 16/10/8.
 */
public class SayAdapter extends BaseAdapter {
    SayBean sayBean;
    Context context;

    public SayAdapter(Context context) {
        this.context = context;
    }

    public void setSayBean(SayBean sayBean) {
        this.sayBean = sayBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sayBean == null ? 0 : sayBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return sayBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.say_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(sayBean.getResult().getList().get(i).getTitle());
        viewHolder.date.setText(sayBean.getResult().getList().get(i).getTime());
        viewHolder.count.setText(sayBean.getResult().getList().get(i).getReplycount()+"评论");
        Picasso.with(context).load(sayBean.getResult().getList().get(i).getSmallpic()).into(viewHolder.iv);
        return view;
    }

    private class ViewHolder {
        private final TextView count;
        private final TextView date;
        private final TextView title;
        private final ImageView iv;
        public ViewHolder(View view) {
            count = (TextView) view.findViewById(R.id.count_say_list);
            date = (TextView) view.findViewById(R.id.date_say_list);
            title = (TextView) view.findViewById(R.id.title_say_list);
            iv = (ImageView) view.findViewById(R.id.iv_say_list);
        }
    }
}
