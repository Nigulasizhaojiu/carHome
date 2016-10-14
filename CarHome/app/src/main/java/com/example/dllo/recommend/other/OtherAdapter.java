package com.example.dllo.recommend.other;

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
public class OtherAdapter extends BaseAdapter {
    OtherBean otherBean;
    Context context;
    int position;

    public void setOtherBean(OtherBean otherBean) {
        this.otherBean = otherBean;
        notifyDataSetChanged();
    }

    public OtherAdapter(Context mContext, int position) {
        this.context = mContext;
        this.position = position;
    }

    @Override
    public int getCount() {
        return otherBean == null ? 0 : otherBean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int i) {
        return otherBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.other_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.count.setText(otherBean.getResult().getNewslist().get(i).getReplycount()+"评论");
        viewHolder.date.setText(otherBean.getResult().getNewslist().get(i).getTime());
        viewHolder.title.setText(otherBean.getResult().getNewslist().get(i).getTitle());
        Picasso.with(context).load(otherBean.getResult().getNewslist().get(i).getSmallpic()).into(viewHolder.iv);
        return view;
    }

    private class ViewHolder {
        private final TextView count;
        private final TextView date;
        private final TextView title;
        private final ImageView iv;
        public ViewHolder(View view) {
            count = (TextView) view.findViewById(R.id.count_other_list);
            date = (TextView) view.findViewById(R.id.date_other_list);
            title = (TextView) view.findViewById(R.id.title_other_list);
            iv = (ImageView) view.findViewById(R.id.iv_other_list);
        }
    }
}
