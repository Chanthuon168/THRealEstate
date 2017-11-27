package com.example.imac.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.imac.myapplication.DetailPagerActivity;
import com.example.imac.myapplication.R;
import com.example.imac.myapplication.adapter.MainPagerAdapter;
import com.example.imac.myapplication.model.Product;

/**
 * Created by Chan Thuon on 11/26/2017.
 */

public class MyFragment extends Fragment {
    private ViewPager pager = null;
    private MainPagerAdapter pagerAdapter = null;
    private Context context;
    private Product product;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);

//        pagerAdapter = new MainPagerAdapter();
//        pager = (ViewPager) view.findViewById(R.id.viewpager);
//        pager.setAdapter(pagerAdapter);
//
////        product = ((DetailPagerActivity)getActivity()).getProduct();
//        LayoutInflater in = getLayoutInflater();
//        RelativeLayout v = (RelativeLayout) in.inflate(R.layout.layout, null);
        int id = getActivity().getIntent().getIntExtra("position", 0);
        Toast.makeText(getActivity(), id+"",Toast.LENGTH_LONG).show();
        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadDataWithBaseURL("", product.getDetail(), "text/html", "UTF-8", "");
//        pagerAdapter.addView(v, 0);
//        pagerAdapter.notifyDataSetChanged();

        return view;
    }
}
