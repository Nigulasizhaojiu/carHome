package com.example.dllo.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.carhome.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<RecommendBean>arrayList = new ArrayList<>();
    public RecommendAdapter(Context context) {
        this.mContext = context;
    }



    public void setArrayList(ArrayList<RecommendBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommend_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendAdapter.MyViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.time.setText(arrayList.get(position).getTime());
        holder.image.setImageResource(arrayList.get(position).getPic());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time,title;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time_recommend);
            title = (TextView) itemView.findViewById(R.id.title_recommend);
            image = (ImageView) itemView.findViewById(R.id.iv_recommend);
        }
    }
}
