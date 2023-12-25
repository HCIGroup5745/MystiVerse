package com.example.luck;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.luck.adapter.RecordAdapter;
import com.example.luck.databinding.ActivityRecordBinding;
import com.example.luck.db.Diary;
import com.example.luck.db.DiaryDao;
import com.example.luck.db.MyDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private ActivityRecordBinding binding;
    private RecordAdapter adapter;
    private Handler handler;
    private List<Diary> diaries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.what == 0X10001) {
                    adapter.setDatas(diaries);
                }
                return false;
            }
        });
        adapter = new RecordAdapter(this);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);
        initData();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Diary diary = new Diary(getCurrentBeijingDate(), getCurrentBeijingTime(), "创建了心情", getRandomString(getResources().getStringArray(R.array.sentens)));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DiaryDao diaryDao = MyDatabase.getInstance(RecordActivity.this).diaryDao();
                        diaryDao.insertDiary(diary);
                    }
                }).start();
                adapter.addData(diary);
            }
        });
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DiaryDao diaryDao = MyDatabase.getInstance(RecordActivity.this).diaryDao();
                diaries = diaryDao.getDiaryList();
                handler.sendEmptyMessage(0X10001);
            }
        }).start();
    }

    public static String getRandomString(String[] stringArray) {
        if (stringArray != null && stringArray.length > 0) {
            // 生成随机索引
            int randomIndex = (int) (Math.random() * stringArray.length);
            return stringArray[randomIndex];
        } else {
            return null; // 或者抛出异常，视情况而定
        }
    }

    // 返回当前北京日期（年/月/日）
    public static String getCurrentBeijingDate() {
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        }
        return currentDate.toString();
    }

    // 返回当前北京时间（24小时制，不包含毫秒）
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentBeijingTime() {
        LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
}