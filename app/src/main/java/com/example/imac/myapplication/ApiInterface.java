package com.example.imac.myapplication;

import com.example.imac.myapplication.model.Product;
import com.example.imac.myapplication.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by imac on 31/10/17.
 */

public interface ApiInterface {
    @GET("get/product")
    Call<List<Product>> getProduct();
    @GET("get/product/{id}")
    Call<List<Product>> getProductById(@Path("id") int id);
    @POST("ldp/video")
    Call<List<Video>> getAllVideo(@Query("page") int pageNumber);
}
