package com.example.imac.myapplication;

import com.example.imac.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by imac on 31/10/17.
 */

public interface ApiInterface {
    @GET("get/product")
    Call<List<Product>> getProduct();
}
