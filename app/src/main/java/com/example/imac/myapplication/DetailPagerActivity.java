package com.example.imac.myapplication;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.imac.myapplication.adapter.MainPagerAdapter;
import com.example.imac.myapplication.adapter.ViewPagerAdapter;
import com.example.imac.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private List<Product> products = new ArrayList<>();
    private int position;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_pager);
        position = getIntent().getIntExtra("position", 0);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Product>> call = service.getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                adapter = new ViewPagerAdapter(DetailPagerActivity.this, products, position);
                mViewPager.setAdapter(adapter);
                mViewPager.setCurrentItem(position);
                tabLayout.setupWithViewPager(mViewPager, true);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
