package com.example.dllo.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.carhome.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/26.
 */
public class DiscoverRvTwoHeadAdapter extends RecyclerView.Adapter<DiscoverRvTwoHeadAdapter.ViewHolder> {
    DiscoverBean discoverBean;
    Context context;
    int id;

    public void setId(int id) {
        this.id = id;
    }

    public DiscoverRvTwoHeadAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.discover_rv_head_two_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(discoverBean.getResult().getCardlist().get(id).getData().get(position).getTitle());
        holder.text.setText(discoverBean.getResult().getCardlist().get(id).getData().get(position).getSubtitle());
        holder.price.setText(discoverBean.getResult().getCardlist().get(id).getData().get(position).getCurrentprice());
        holder.fctPrice.setText(discoverBean.getResult().getCardlist().get(id).getData().get(position).getPrice());
        Picasso.with(context).load(discoverBean.getResult().getCardlist().get(id).getData().get(position).getImageurl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0: discoverBean.getResult().getCardlist().get(id).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView title;
        private final TextView text;
        private final TextView price;
        private final TextView fctPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_discover_two_head_list);
            title = (TextView) itemView.findViewById(R.id.title_discover_two_head_list);
            text = (TextView) itemView.findViewById(R.id.text_discover_two_head_list);
            price = (TextView) itemView.findViewById(R.id.price_discover_two_head_list);
            fctPrice = (TextView) itemView.findViewById(R.id.fctprice_discover_two_head_list);
        }
    }
}
