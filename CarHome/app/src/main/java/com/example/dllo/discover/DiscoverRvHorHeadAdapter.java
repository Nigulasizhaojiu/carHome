package com.example.dllo.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.carhome.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/27.
 */
public class DiscoverRvHorHeadAdapter extends RecyclerView.Adapter<DiscoverRvHorHeadAdapter.ViewHolder> {
    DiscoverBean discoverBean;
    Context context;
    int id;

    public DiscoverRvHorHeadAdapter(Context context) {
        this.context = context;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public DiscoverRvHorHeadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hor_recycler_head_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiscoverRvHorHeadAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(discoverBean.getResult().getCardlist().get(id).getData().get(position).getImageurl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0: discoverBean.getResult().getCardlist().get(id).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_hor_head);
        }
    }
}
