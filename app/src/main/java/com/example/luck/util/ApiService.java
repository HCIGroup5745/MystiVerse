package com.example.luck.util;

import com.example.luck.entity.JsonRootBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("query")
    Observable<JsonRootBean> getInfo(@Query("keyword") String keyword,@Query("key") String key);

}
