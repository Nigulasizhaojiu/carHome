package com.example.dllo.recommend;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendBean {
    String title,time;
    String url;
    int pic;

    public RecommendBean() {

    }


    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
