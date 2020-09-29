package com.mad.trafficclient.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:51
 */
@DatabaseTable(tableName = "czjl")
public class Czjl {
    @DatabaseField (generatedId = true)
    private int id;
    @DatabaseField (columnName = "ch")
    private String ch;
    @DatabaseField (columnName = "name")
    private String name;
    @DatabaseField (columnName = "je")
    private int je;
    @DatabaseField (columnName = "time")
    private String time;

    public Czjl() {
    }

    public Czjl(String ch, String name, int je, String time) {
        this.ch = ch;
        this.name = name;
        this.je = je;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJe() {
        return je;
    }

    public void setJe(int je) {
        this.je = je;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
