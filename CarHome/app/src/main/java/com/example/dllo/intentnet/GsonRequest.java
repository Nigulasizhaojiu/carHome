package com.example.dllo.intentnet;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/9/20.
 * 可以直接从网络请求解析成实体类 然后返回
 */
public class GsonRequest<T> extends Request<T>{
    private final Response.Listener<T> mListener;
    private Class<T>mTclass;

    public GsonRequest(int method,
                       String url,
                       Class<T>clazz,//为了解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mTclass = clazz;
    }

    public GsonRequest(String url,
                       Class<T>clazz,//为了解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener){
        this(Method.GET,url,clazz,mListener,listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        Gson gson = new Gson();
        Log.d("GsonRequest", parsed);
        Log.d("GsonRequest", mTclass.getSimpleName());
        return Response.success(gson.fromJson(parsed,mTclass), HttpHeaderParser.parseCacheHeaders(response));
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    //网络拉取失败 就拉取缓存
    @Override
    public void deliverError(VolleyError error) {
        if (error instanceof NoConnectionError) {
            Cache.Entry entry = this.getCacheEntry();
            if (entry != null) {
                Log.d("数据", "这是缓存数据");
                Response<T> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                deliverResponse(response.result);
                return;
            }
        }
        super.deliverError(error);

    }
}
