package com.example.luck;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.luck.databinding.ActivityLuckBinding;
import com.example.luck.db.Diary;
import com.example.luck.db.DiaryDao;
import com.example.luck.db.MyDatabase;
import com.example.luck.entity.JsonRootBean;
import com.example.luck.entity.Result;
import com.example.luck.util.ApiService;
import com.example.luck.util.MyRetrofit;
import com.xuexiang.xui.utils.XToastUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LuckActivity extends AppCompatActivity {
    private ActivityLuckBinding binding;

    private String zodiac = "射手座";
    private String key = "7d5df1a57806f2905561ab16744a388d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLuckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        zodiac = getIntent().getStringExtra("zodiac");

        initData(zodiac);

        binding.back.setOnClickListener(view -> onBackPressed());

    }

    private void initData(String zodiac) {
        ApiService apiService = MyRetrofit.getTest().create(ApiService.class);
        apiService.getInfo(zodiac, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonRootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonRootBean resultBaseResponse) {
                        if (resultBaseResponse.getError_code() == 0) {
                            binding.tip1.setVisibility(View.GONE);
                            binding.ll.setVisibility(View.VISIBLE);
                            updateUI(resultBaseResponse.getResult());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    DiaryDao diaryDao = MyDatabase.getInstance(LuckActivity.this).diaryDao();
                                    diaryDao.insertDiary(new Diary(getCurrentBeijingDate(), getCurrentBeijingTime(), "完成了测试", getRandomString(getResources().getStringArray(R.array.sentens))));
                                }
                            }).start();
                        } else {
                            binding.ll.setVisibility(View.GONE);
                            binding.tip1.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 处理请求失败的情况
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void updateUI(Result result) {
        binding.name.setText("星座：" + result.getName());
        binding.sssx.setText("属性：" + result.getSssx());
        binding.range.setText("星座公历日期范围：" + result.getRange());
        binding.zxtd.setText("特点：" + result.getZxtd());
        binding.zggw.setText("掌管宫位：" + result.getZggw());
        binding.yysx.setText("阴阳性：" + result.getYysx());
        binding.zdtz.setText("最大特征：" + result.getZdtz());
        binding.zgxx.setText("主管星：" + result.getZgxx());
        binding.xyys.setText("颜色：" + result.getXyys());
        binding.xsfg.setText("行事风格："+result.getXsfg());
        binding.jssw.setText("珠宝：" + result.getJssw());
        binding.xyhm.setText("幸运号码：" + result.getXyhm());
        binding.bx.setText("表现：" + result.getBx());
        binding.yd.setText("优点：" + result.getYd());
        binding.qd.setText("缺点：" + result.getQd());
        binding.jbtz.setText("基本特质：" + result.getJbtz());
        binding.jttz.setText("具体特质：" + result.getJbtz());
        binding.gxmd.setText("个性缺点：" + result.getGxmd());
        binding.zj.setText("总体评价：" + result.getZj());
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