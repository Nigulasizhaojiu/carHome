package com.example.dllo.forum.twentyfour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.carhome.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/22.
 */
public class TwentyFourAdapter extends BaseAdapter {
    TwentyFourBean twentyFourBean;
    Context context;

    public TwentyFourAdapter(Context context) {
        this.context = context;
    }

    public void setTwentyFourBean(TwentyFourBean twentyFourBean) {
        this.twentyFourBean = twentyFourBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return twentyFourBean== null ? 0 : twentyFourBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return twentyFourBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.twentyfour_list,null);
            viewholder = new Viewholder(view);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        if (twentyFourBean.getResult().getList().get(i).getNickname().equals("")){
            viewholder.text.setText("");
        }else {
            viewholder.text.setText(twentyFourBean.getResult().getList().get(i).getTopicinfo());
        }
        if (twentyFourBean.getResult().getList().get(i).getAuthseries().equals("")){
            viewholder.page.setText("");
        }else {
            viewholder.page.setText(twentyFourBean.getResult().getList().get(i).getAuthseries());
        }
        if (twentyFourBean.getResult().getList().get(i).getHeadimg().equals("")){
            viewholder.iv.setImageResource(R.mipmap.ahlib_userpic_default);
        }else {
            Picasso.with(context).load(twentyFourBean.getResult().getList().get(i).getHeadimg()).into(viewholder.iv);
        }
        viewholder.name.setText(twentyFourBean.getResult().getList().get(i).getNickname());
        viewholder.time.setText(twentyFourBean.getResult().getList().get(i).getPostdate().substring(5,16));
        viewholder.title.setText(twentyFourBean.getResult().getList().get(i).getTitle());
        viewholder.forum.setText(twentyFourBean.getResult().getList().get(i).getBbsname());
        viewholder.count.setText(twentyFourBean.getResult().getList().get(i).getReplycounts()+"回");


        return view;
    }

    private class Viewholder {

        private final TyFourHoursCircleImage iv;
        private final TextView name;
        private final TextView page;
        private final TextView time;
        private final TextView title;
        private final TextView text;
        private final TextView forum;
        private final TextView count;

        public Viewholder(View view) {
            iv = (TyFourHoursCircleImage) view.findViewById(R.id.iv_twentyfour);
            name = (TextView) view.findViewById(R.id.name_twentyfour);
            page = (TextView) view.findViewById(R.id.page_twentyfour);
            time = (TextView) view.findViewById(R.id.time_twentyfour);
            title = (TextView) view.findViewById(R.id.title_twentyfour);
            text = (TextView) view.findViewById(R.id.text_twentyfour);
            forum = (TextView) view.findViewById(R.id.forum_twentyfour);
            count = (TextView) view.findViewById(R.id.count_twentyfour);

        }
    }
}
