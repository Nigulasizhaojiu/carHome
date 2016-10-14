package com.example.dllo.recommend.tv;

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
 * Created by dllo on 16/10/9.
 */
public class TvAdapter extends BaseAdapter {
    TvBean tvBean;
    Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public void setTvBean(TvBean tvBean) {
        this.tvBean = tvBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tvBean == null ? 0 :tvBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return tvBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.tv_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(tvBean.getResult().getList().get(i).getTitle());
        viewHolder.count.setText(tvBean.getResult().getList().get(i).getReplycount()+"评论");
        viewHolder.play.setText(Math.round((tvBean.getResult().getList().get(i).getPlaycount()/10000.0)*10)/10.0+"万播放");
        viewHolder.date.setText(tvBean.getResult().getList().get(i).getTime());
        Picasso.with(context).load(tvBean.getResult().getList().get(i).getSmallimg()).into(viewHolder.iv);
        return view;
    }

    private class ViewHolder {
        private final TextView count;
        private final TextView date;
        private final TextView play;
        private final TextView title;
        private final ImageView iv;
        public ViewHolder(View view) {
            count = (TextView) view.findViewById(R.id.count_tv_list);
            date = (TextView) view.findViewById(R.id.date_tv_list);
            play = (TextView) view.findViewById(R.id.play_count_tv_list);
            title = (TextView) view.findViewById(R.id.title_tv_list);
            iv = (ImageView) view.findViewById(R.id.iv_tv_list);
        }
    }
}
