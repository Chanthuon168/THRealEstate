package com.example.imac.myapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.imac.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chan Thuon on 9/13/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {
    Context ssContext;
    private List<Product> products = new ArrayList<>();
    int position;

    public ViewPagerAdapter(Context ssContext, List<Product> products, int position) {
        this.ssContext = ssContext;
        this.products = products;
        this.position = position;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean isViewFromObject(View ssView, Object ssObject) {
        return ssView == ((WebView) ssObject);
    }

    @Override
    public Object instantiateItem(ViewGroup ssContainer, int ssPosition) {
        WebView webView = new WebView(ssContext);
        webView.setPadding(0, 0, 0, 0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", products.get(ssPosition).getDetail(), "text/html", "UTF-8", "");

        ((ViewPager) ssContainer).addView(webView, 0);
        return webView;
    }

    @Override
    public void destroyItem(ViewGroup ssContainer, int ssPosition,
                            Object ssObject) {
        ((ViewPager) ssContainer).removeView((WebView) ssObject);
    }
}
