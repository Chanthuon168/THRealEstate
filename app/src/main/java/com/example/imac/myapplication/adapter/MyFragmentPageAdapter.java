package com.example.imac.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.imac.myapplication.fragment.MyFragment;
import com.example.imac.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imac on 27/11/17.
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    public static int pos = 0;

    private List<Fragment> myFragments;
    private List<Product> products;
    private Context context;

    public MyFragmentPageAdapter(Context c, FragmentManager fragmentManager, List<Fragment> myFrags, List<Product> products) {
        super(fragmentManager);
        myFragments = myFrags;
        this.products = products;
        this.context = c;
    }

    @Override
    public Fragment getItem(int position) {

        return myFragments.get(position);

    }

    @Override
    public int getCount() {

        return myFragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        setPos(position);
//        return categories.get(position);
//    }

    public static int getPos() {
        return pos;
    }

//    public void add(Class<MyFragment> c, String title, Bundle b) {
//        myFragments.add(Fragment.instantiate(context,c.getName(),b));
//        categories.add(title);
//    }

    public static void setPos(int pos) {
        MyFragmentPageAdapter.pos = pos;
    }
}