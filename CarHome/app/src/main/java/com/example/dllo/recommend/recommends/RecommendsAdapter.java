package com.example.dllo.recommend.recommends;

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
public class RecommendsAdapter extends BaseAdapter{
    RecommendsBean recommendsBean;
    Context context;
    private final int TYPE_COUNT = 2;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_THREE_IMAGE = 1;

    public RecommendsAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendsBean(RecommendsBean recommendsBean) {
        this.recommendsBean = recommendsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recommendsBean == null ? 0 : recommendsBean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int i) {
        return recommendsBean;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (recommendsBean.getResult().getNewslist().get(position).getMediatype() == 6){
            return TYPE_THREE_IMAGE;
        }else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        ViewHolder1 viewHolder1 = null;
        int type = getItemViewType(i);
        if (view == null) {
            if (type == TYPE_THREE_IMAGE) {
                view = LayoutInflater.from(context).inflate(R.layout.item_recommend_img, null);
                viewHolder1 = new ViewHolder1(view);
                view.setTag(viewHolder1);
            } else if (type == TYPE_NORMAL) {
                view = LayoutInflater.from(context).inflate(R.layout.recommends_list, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
        } else {
            if (type == TYPE_THREE_IMAGE) {
                viewHolder1 = (ViewHolder1) view.getTag();
            } else if (type == TYPE_NORMAL) {
                viewHolder = (ViewHolder) view.getTag();
            }
        }
        if (type == TYPE_THREE_IMAGE) {

            String url = recommendsBean.getResult().getNewslist().get(i).getIndexdetail();
            String[] args = url.split("㊣");
            String[] args1 = args[2].split(",");
            String one = args1[0];
            String two = args1[1];
            String three = args1[2];

            Picasso.with(context).load(one).into(viewHolder1.imgIvImageOne);
            Picasso.with(context).load(two).into(viewHolder1.imgIvImageTwo);
            Picasso.with(context).load(three).into(viewHolder1.imgIvImageThree);

            viewHolder1.imgTvTitle.setText(recommendsBean.getResult().getNewslist().get(i).getTitle());
            viewHolder1.imgTvComment.setText(recommendsBean.getResult().getNewslist().get(i).getReplycount() + "图片");
            viewHolder1.imgTvDate.setText(recommendsBean.getResult().getNewslist().get(i).getTime());
        } else if(type == TYPE_NORMAL){
            viewHolder.title.setText(recommendsBean.getResult().getNewslist().get(i).getTitle());
            viewHolder.date.setText(recommendsBean.getResult().getNewslist().get(i).getTime());
            if (!(recommendsBean.getResult().getNewslist().get(i).getSmallpic().equals(""))) {
                Picasso.with(context).load(recommendsBean.getResult().getNewslist().get(i).getSmallpic()).into(viewHolder.iv);
            }
            if (recommendsBean.getResult().getNewslist().get(i).getMediatype() == 3) {
                viewHolder.count.setText(recommendsBean.getResult().getNewslist().get(i).getReplycount() + "播放");
            } else if (recommendsBean.getResult().getNewslist().get(i).getMediatype() == 2) {
                viewHolder.count.setText(recommendsBean.getResult().getNewslist().get(i).getReplycount() + "评论");
            } else if (recommendsBean.getResult().getNewslist().get(i).getMediatype() == 1) {
                viewHolder.count.setText(recommendsBean.getResult().getNewslist().get(i).getReplycount() + "评论");
            }

        }
        return view;
    }

    private class ViewHolder {

        private final TextView count;
        private final TextView date;
        private final TextView title;
        private final ImageView iv;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.iv_recommends_list);
            count = (TextView) view.findViewById(R.id.count_recommends_list);
            date = (TextView) view.findViewById(R.id.date_recommends_list);
            title = (TextView) view.findViewById(R.id.title_recommends_list);

        }
    }

    private class ViewHolder1 {
        private final ImageView imgIvImageOne;
        private final ImageView imgIvImageTwo;
        private final ImageView imgIvImageThree;
        private final TextView imgTvDate;
        private final TextView imgTvComment;
        private final TextView imgTvTitle;

        public ViewHolder1(View view) {

            imgIvImageOne = (ImageView) view.findViewById(R.id.item_recommend_img_image_one);
            imgIvImageTwo = (ImageView) view.findViewById(R.id.item_recommend_img_image_two);
            imgIvImageThree = (ImageView) view.findViewById(R.id.item_recommend_img_image_three);
            imgTvDate = (TextView) view.findViewById(R.id.item_recommend_img_date);
            imgTvComment = (TextView) view.findViewById(R.id.item_recommend_img_comment);
            imgTvTitle = (TextView) view.findViewById(R.id.item_recommend_img_title);
        }
    }
}
