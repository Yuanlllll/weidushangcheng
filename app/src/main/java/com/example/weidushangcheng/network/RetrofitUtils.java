package com.example.weidushangcheng.network;

import android.util.Log;

import com.example.weidushangcheng.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private final Retrofit retrofit;

    private static final class SINGLE_INSTANCE{
        public static final RetrofitUtils _INSTANCE = new RetrofitUtils();
    }
    //方法请求
    public static RetrofitUtils getInstance(){
        return SINGLE_INSTANCE._INSTANCE;
    }
    //
    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BOTH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildkhttpClient())
                .build();
    }

    //设置读取超时
    private OkHttpClient buildkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数,请求结果
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(3000,TimeUnit.MILLISECONDS)
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .build();
    }

    public Retrofit getRetrfit(){
        return retrofit;
    }

    public  <T> T creat(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
