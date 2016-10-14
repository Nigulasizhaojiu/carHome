package com.example.dllo.recommend.fastnews;

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
public class FastNewsAdapter extends BaseAdapter {
    FastNewsBean fastNewsBean;
    Context context;

    public FastNewsAdapter(Context context) {
        this.context = context;
    }

    public void setFastNewsBean(FastNewsBean fastNewsBean) {
        this.fastNewsBean = fastNewsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fastNewsBean == null ? 0 : fastNewsBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return fastNewsBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fastnews_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.date.setText(fastNewsBean.getResult().getList().get(i).getCreatetime());
        viewHolder.title.setText(fastNewsBean.getResult().getList().get(i).getTitle());
        viewHolder.count.setText(Math.round((fastNewsBean.getResult().getList().get(i).getReviewcount()/10000.0)*10)/10.0+"万位观众");
        Picasso.with(context).load(fastNewsBean.getResult().getList().get(i).getBgimage()).resize(360,150).centerCrop().into(viewHolder.iv);

        return view;
    }

    private class ViewHolder {

        private final TextView date;
        private final TextView count;
        private final TextView title;
        private final ImageView iv;

        public ViewHolder(View view) {
            date = (TextView) view.findViewById(R.id.date_fastnews_list);
            count = (TextView) view.findViewById(R.id.count_fastnews);
            title = (TextView) view.findViewById(R.id.title_fastnews);
            iv = (ImageView) view.findViewById(R.id.iv_fastnews);
        }
    }
}
