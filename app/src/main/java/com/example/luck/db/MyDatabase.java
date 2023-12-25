package com.example.luck.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Diary.class}, version = 1,exportSchema = false)//指定数据库的表
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "my_db";//指定数据库名

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getInstance(Context context)
    {
        if(databaseInstance == null)
        {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;//初始化数据库
    }

    public abstract DiaryDao diaryDao();
}
