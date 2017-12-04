package com.example.imac.myapplication.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imac.myapplication.R;
import com.example.imac.myapplication.model.Video;

import java.util.List;

/**
 * Created by Chan Thuon on 12/4/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private GridLayoutManager mManager;
    private RecyclerView mRecyclerView;

    private List<Video> videos;

    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public UserViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

    public MyAdapter(List<Video> videos, RecyclerView recyclerView) {
        this.videos = videos;
        this.mManager = (GridLayoutManager) recyclerView.getLayoutManager();
        this.mRecyclerView = recyclerView;
        this.mManager = (GridLayoutManager) recyclerView.getLayoutManager();
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return getItemViewType(position) == VIEW_PROG ? mManager.getSpanCount() : 1;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return videos.get(position)!=null? VIEW_ITEM: VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType==VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);

            vh = new UserViewHolder(v);
        }else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UserViewHolder){
            UserViewHolder textViewHolder = (UserViewHolder) holder;
            textViewHolder.title.setText(""+videos.get(position).getId());
        }else{
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}