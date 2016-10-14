package com.example.dllo.mfw;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/13.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView ;
    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_img);
        textView = (TextView) itemView.findViewById(R.id.number);
    }
//    public void bind(Integer resId,int position) {
//        Bitmap bitmap = BitmapCache.INSTANCE.getBitmap(resId);
//        HiveDrawable drawable = new HiveDrawable(HiveLayoutManager.VERTICAL,bitmap);
//        imageView.setImageDrawable(drawable);
//        textView.setText(String.valueOf(position));
//        textView.setVisibility(View.GONE);
//    }
}
