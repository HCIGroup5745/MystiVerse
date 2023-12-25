package com.example.luck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QQperate {
    //添加用户
    public static int insert(Context context, String account,String password) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account",account);
        values.put("password",password);
        String sql = "select * from user where account=?";
        Cursor cursor = db.rawQuery(sql, new String[]{account});
        int f = 0;
        if(cursor.getCount() == 0) {
            db.insert("user",null,values);
            f = 1;
        }
        db.close();
        cursor.close();
        return f;
    }

    //查询用户
    public static int query(Context context, String account, String password) {
        QQHelper qqHelper = new QQHelper(context);
        SQLiteDatabase db = qqHelper.getWritableDatabase();
        String sql = "select * from user where account=? and password=?";
        Cursor cursor = db.rawQuery(sql,new String[]{account,password});
        int f = 0;
        if(cursor.getCount() != 0) f = 1;
        db.close();
        cursor.close();
        return f;
    }
}