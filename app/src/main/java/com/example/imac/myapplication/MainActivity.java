package com.example.imac.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.imac.myapplication.fragment.FragmentFavourite;
import com.example.imac.myapplication.fragment.FragmentHome;
import com.example.imac.myapplication.fragment.FragmentNotification;
import com.example.imac.myapplication.fragment.FragmentSearch;
import com.example.imac.myapplication.fragment.FragmentUrgent;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class MainActivity extends AppCompatActivity {

    private FragmentHome fragmentHome;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentHome = new FragmentHome();
                    replaceFragment(fragmentHome);
                    return true;
                case R.id.navigation_urgent:
                    FragmentUrgent fragmentUrgent = new FragmentUrgent();
                    replaceFragment(fragmentUrgent);
                    return true;
                case R.id.navigation_search:
                    FragmentSearch fragmentSearch = new FragmentSearch();
                    replaceFragment(fragmentSearch);
                    return true;
                case R.id.navigation_favorite:
                    FragmentFavourite fragmentFavourite = new FragmentFavourite();
                    replaceFragment(fragmentFavourite);
                    return true;
                case R.id.navigation_notifications:
                    FragmentNotification fragmentNotification = new FragmentNotification();
                    replaceFragment(fragmentNotification);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initScreen();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initScreen() {
        fragmentHome = new FragmentHome();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.layout_container, fragmentHome).commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_container, fragment).commit();
    }
}
