package com.example.dllo.discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.carhome.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/27.
 */
public class DiscoverBtnHeadAdapter extends RecyclerView.Adapter<DiscoverBtnHeadAdapter.ViewHolder> {
    DiscoverBean discoverBean;
    Context context;
    int id;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public DiscoverBean getDiscoverBean() {
        return discoverBean;
    }

    public void setDiscoverBean(DiscoverBean discoverBean) {
        this.discoverBean = discoverBean;
        notifyDataSetChanged();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DiscoverBtnHeadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_btn_head_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(discoverBean.getResult().getCardlist().get(id).getData().get(position).getImageurl()).into(holder.iv);
        holder.title.setText(discoverBean.getResult().getCardlist().get(id).getData().get(position).getTitle());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClickListener.click(position,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return discoverBean == null ? 0: discoverBean.getResult().getCardlist().get(id).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView title;
        private final LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_btn_discover_head_list);
            title = (TextView) itemView.findViewById(R.id.tv_btn_discover_head_list);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_btn_rv);
        }
    }
}
