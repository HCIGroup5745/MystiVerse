package com.example.luck.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")//指定数据库表名
public class Diary {
    @PrimaryKey(autoGenerate = true)//自动生成id  唯一主键
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)//类型是Integer型
    private int id;
    @ColumnInfo(name = "time", typeAffinity = ColumnInfo.TEXT)
    private String time;
    @ColumnInfo(name = "time1", typeAffinity = ColumnInfo.TEXT)
    private String time1;
    @ColumnInfo(name = "tip", typeAffinity = ColumnInfo.TEXT)
    private String tip;
    @ColumnInfo(name = "content", typeAffinity = ColumnInfo.TEXT)
    private String content;

    public Diary() {
    }

    @Ignore
    public Diary(String time, String time1, String tip, String content) {
        this.time = time;
        this.time1 = time1;
        this.tip = tip;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", time1='" + time1 + '\'' +
                ", tip='" + tip + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
