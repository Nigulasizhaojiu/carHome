package com.example.dllo.forum.allselected;

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
 * Created by dllo on 16/9/22.
 */
public class AllSelectedAdapter extends BaseAdapter {
    AllSelectedBean allSelectedBean;
    Context context;

    public AllSelectedAdapter(Context context) {
        this.context = context;
    }

    public void setAllSelectedBean(AllSelectedBean allSelectedBean) {
        this.allSelectedBean = allSelectedBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return allSelectedBean == null ? 0 : allSelectedBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return allSelectedBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //season title iv count

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.allselected_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.count.setText(allSelectedBean.getResult().getList().get(i).getReplycounts()+"回帖");
        viewHolder.season.setText(allSelectedBean.getResult().getList().get(i).getBbsname());
        viewHolder.title.setText(allSelectedBean.getResult().getList().get(i).getTitle());
        Picasso.with(context).load(allSelectedBean.getResult().getList().get(i).getSmallpic()).into(viewHolder.iv);

        return view;
    }

    public void setBean1(AllSelectedBean bean1) {
        allSelectedBean.getResult().getList().addAll(bean1.getResult().getList());
        notifyDataSetChanged();
    }

    private class ViewHolder {

        private final TextView count;
        private final TextView season;
        private final TextView title;
        private final ImageView iv;

        public ViewHolder(View view) {
            count = (TextView) view.findViewById(R.id.count_allselected);
            season = (TextView) view.findViewById(R.id.season_allselected);
            title = (TextView) view.findViewById(R.id.title_allselected);
            iv = (ImageView) view.findViewById(R.id.iv_allselected);
        }
    }
}
