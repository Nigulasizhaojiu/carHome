package com.example.dllo.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.carhome.HistoryBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/12.
 */
public class DBTools{

    SQLiteDatabase database;

    public DBTools(Context context) {
        MyHelper helper = new MyHelper(context,"Car.db",null,1);
        database = helper.getWritableDatabase();
    }
    //添加
    public void insertDB(HistoryBean historyBean){
        ContentValues values = new ContentValues();
        values.put("cars",historyBean.getName());
        database.insert("car",null,values);
    }
    //删除
    public void deleteAllDB(){
        database.delete("car",null,null);
    }
    //查找
    public ArrayList queryAllDB(){
        ArrayList<HistoryBean>historyBeen = new ArrayList<>();
        Cursor cursor = database.query("car",null,null,null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("cars"));
                HistoryBean bean = new HistoryBean(name);
                historyBeen.add(bean);
            }
        }
        return historyBeen;
    }

}
