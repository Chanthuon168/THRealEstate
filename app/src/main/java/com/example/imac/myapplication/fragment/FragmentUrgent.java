package com.example.imac.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imac.myapplication.ApiClient;
import com.example.imac.myapplication.ApiInterface;
import com.example.imac.myapplication.EndRecyclerOnScrollListener;
import com.example.imac.myapplication.EndlessRecyclerOnScrollListener;
import com.example.imac.myapplication.OnLoadMoreListener;
import com.example.imac.myapplication.R;
import com.example.imac.myapplication.TestActivity;
import com.example.imac.myapplication.adapter.AdapterProductItem;
import com.example.imac.myapplication.adapter.MyAdapter;
import com.example.imac.myapplication.model.Contact;
import com.example.imac.myapplication.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by imac on 21/11/17.
 */

public class FragmentUrgent extends Fragment {
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    int videoPage = 1;
    List<Video> videoList;
    private Video video;
    private List<Video> videos = new ArrayList<>();
    private Handler handler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_urgent, container, false);

        handler = new Handler();
        videoList = new ArrayList<>();

        getVideoAll(videoPage);

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(videoList, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(final int current_page) {
                videoList.add(null);
                mAdapter.notifyItemInserted(videoList.size());

                videoPage++;
                getVideoAll(current_page);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //remove progress item
                        videoList.remove(videoList.size() - 1);
                        mAdapter.notifyItemRemoved(videoList.size());
                        //add items one by one
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2500);
            }
        });


        return view;
    }

    private void getVideoAll(final int page) {
        video = new Video("");
        ApiInterface service = ApiClient.getClientApi().create(ApiInterface.class);
        Call<List<Video>> callComment = service.getAllVideo(page);
        callComment.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videos = response.body();
                if (videos.size() > 0) {
                    videoList.addAll(response.body());
                    mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), videoList.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
