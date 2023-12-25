package com.example.luck.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DiaryDao {
    @Insert
    //插入
    void insertDiary(Diary Diary);

    @Delete
    //删除
    void deleteDiary(Diary Diary);

    @Update
    //修改
    void updateDiary(Diary Diary);

    @Query("SELECT * FROM diary order by id desc")//查询所有书籍
    List<Diary> getDiaryList();
}
