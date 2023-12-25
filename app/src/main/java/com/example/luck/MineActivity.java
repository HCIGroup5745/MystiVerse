package com.example.luck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luck.databinding.ActivityMineBinding;
import com.example.luck.util.SharedPreferencesUtil;
import com.xuexiang.citypicker.CityPicker;
import com.xuexiang.citypicker.adapter.OnLocationListener;
import com.xuexiang.citypicker.adapter.OnPickListener;
import com.xuexiang.citypicker.model.City;
import com.xuexiang.citypicker.model.HotCity;
import com.xuexiang.citypicker.model.LocateState;
import com.xuexiang.citypicker.model.LocatedCity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MineActivity extends AppCompatActivity {
    private ActivityMineBinding binding;
    private Calendar calendar;
    List<HotCity> hotCities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initView();
        binding.nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        binding.birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityPicker.from(MineActivity.this)
                        .enableAnimation(true)
                        .setLocatedCity(null)
                        .setHotCities(hotCities)
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                binding.address.setText(data.getName());
                                SharedPreferencesUtil.saveString(MineActivity.this, "address", data.getName());
                            }

                            @Override
                            public void onLocate(OnLocationListener listener) {

                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        binding.nowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityPicker.from(MineActivity.this)
                        .enableAnimation(true)
                        .setLocatedCity(null)
                        .setHotCities(hotCities)
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                binding.nowAddress.setText(data.getName());
                                SharedPreferencesUtil.saveString(MineActivity.this, "nowAddress", data.getName());
                            }

                            @Override
                            public void onLocate(OnLocationListener listener) {

                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        binding.toLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, LuckActivity.class);
                intent.putExtra("zodiac", SharedPreferencesUtil.getString(MineActivity.this, "zodiac", "射手座"));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        binding.nickname.setText(SharedPreferencesUtil.getString(MineActivity.this, "nickname", "还没有名字哦"));
        binding.birth.setText(SharedPreferencesUtil.getString(MineActivity.this, "birth", "2000/1/1"));
        binding.address.setText(SharedPreferencesUtil.getString(MineActivity.this, "address", "北京"));
        binding.nowAddress.setText(SharedPreferencesUtil.getString(MineActivity.this, "nowAddress", "北京"));
    }

    private void initData() {
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
    }

    private void showInputDialog() {
        // 创建AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);

        // 获取布局填充器
        LayoutInflater inflater = getLayoutInflater();

        // 加载布局文件
        View view = inflater.inflate(R.layout.dialog_input, null);

        // 获取布局中的EditText
        final EditText editText = view.findViewById(R.id.editText);

        // 设置对话框的视图
        builder.setView(view);

        // 设置对话框的标题
        builder.setTitle("输入对话框");

        // 设置确定按钮的点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputText = editText.getText().toString();
                // 处理输入的文本
                if (TextUtils.isEmpty(inputText)) {
                    Toast.makeText(MineActivity.this, "输入为空，无更改", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                SharedPreferencesUtil.saveString(MineActivity.this, "nickname", inputText);
                binding.nickname.setText(inputText);
            }
        });

        // 设置取消按钮的点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 取消按钮的点击事件
                dialog.dismiss();
            }
        });

        // 创建并显示对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDatePickerDialog() {
        calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, R.style.AlertDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        // 处理选择的日期
                        String selectedDate = selectedYear + "/" + (selectedMonth + 1) + "/" + selectedDayOfMonth;
                        binding.birth.setText(selectedDate);
                        SharedPreferencesUtil.saveString(MineActivity.this, "birth", selectedDate);
                        SharedPreferencesUtil.saveString(MineActivity.this, "zodiac", getZodiacSign(selectedDate));
                    }
                },
                year, month, dayOfMonth
        );

        datePickerDialog.show();
    }

    public String getZodiacSign(String birthDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

        try {
            Date date = dateFormat.parse(birthDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1; // 月份是从 0 开始的，需要加 1

            if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
                return "白羊座";
            } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
                return "金牛座";
            } else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
                return "双子座";
            } else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
                return "巨蟹座";
            } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
                return "狮子座";
            } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
                return "处女座";
            } else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
                return "天秤座";
            } else if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) {
                return "天蝎座";
            } else if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) {
                return "射手座";
            } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
                return "摩羯座";
            } else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
                return "水瓶座";
            } else {
                return "双鱼座";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "日期格式错误";
        }
    }
}