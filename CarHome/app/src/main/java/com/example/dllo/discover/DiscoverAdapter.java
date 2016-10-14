package com.example.dllo.discover;

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
 * Created by dllo on 16/9/23.
 */
public class DiscoverAdapter extends BaseAdapter{
    DiscoverBean discoverBean;
    Context context;
    int id;

    public void setId(int id) {
        this.id = id;
    }

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return discoverBean == null ? 0 : discoverBean.getResult().getCardlist().size();
    }

    @Override
    public Object getItem(int i) {
        return discoverBean.getResult().getCardlist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.discover_list,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.adinfo.setText(discoverBean.getResult().getCardlist().get(id).getData().get(i).getAdinfo());
        viewHolder.price.setText(discoverBean.getResult().getCardlist().get(id).getData().get(i).getPrice());
        viewHolder.fctprice.setText(discoverBean.getResult().getCardlist().get(id).getData().get(i).getFctprice());
        viewHolder.title.setText(discoverBean.getResult().getCardlist().get(id).getData().get(i).getTitle());
        Picasso.with(context).load(discoverBean.getResult().getCardlist().get(id).getData().get(i).getLogo()).into(viewHolder.iv);
        return view;
    }

    private class ViewHolder {

        private final ImageView iv;
        private final TextView fctprice;
        private final TextView title;
        private final TextView price;
        private final TextView adinfo;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.iv_discover_list);
            fctprice = (TextView) view.findViewById(R.id.fctprice_discover_list);
            title = (TextView) view.findViewById(R.id.title_discover_list);
            price = (TextView) view.findViewById(R.id.price_discover_list);
            adinfo = (TextView) view.findViewById(R.id.adinfo_discover_list);

        }
    }
}
