package com.example.dllo.carhome;

/**
 * Created by dllo on 16/10/12.
 */
public class HistoryBean {
    String name;
    int id;

    public HistoryBean(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
