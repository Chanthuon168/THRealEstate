package com.example.imac.myapplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by imac on 31/10/17.
 */

public class ApiClient {
    public static final String BASE_URL = "http://hammercolab.com/api/";
    public static final String BASE_URL_API = "http://hammercolab.com/CartoonApi/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit_api = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientApi() {
        if (retrofit_api == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(interceptor).build();
            retrofit_api = new Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_api;
    }
}
