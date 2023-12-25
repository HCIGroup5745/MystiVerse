package com.example.luck.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public static Retrofit getTest() {
        return new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/fapig/constellation/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();// 这里换成RxJava3CallAdapterFactory.create()
    }
}
