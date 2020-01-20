package com.jiachang.facedebug.http;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mickey.Ma
 * @date 2020-01-07
 * @description
 */
public class ApiRetrofit {
    /**
     * 初始化retrofit
     */
    public static RetrofitInterface initRetrofit(String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w("okHttp",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);//Level中还有其他等级
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging) //添加拦截器
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //声明兼容Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//声明兼容rxJava
                .client(client) //载入拦截器
                .build();
        //创建Retrofit接口对象
        return retrofit.create(RetrofitInterface.class);
    }
}
