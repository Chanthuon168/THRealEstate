package com.example.imac.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imac.myapplication.ApiClient;
import com.example.imac.myapplication.ApiInterface;
import com.example.imac.myapplication.EndlessRecyclerOnScrollListener;
import com.example.imac.myapplication.OnLoadMoreListener;
import com.example.imac.myapplication.R;
import com.example.imac.myapplication.adapter.AdapterProductItem;
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
    private List<Contact> contacts;
    private AdapterProductItem contactAdapter;
    private Random random;
    int videoPage = 1;
    List<Video> videoList;
    private Video video;
    private List<Video> videos = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_urgent, container, false);

        videoList = new ArrayList<>();

        getVideoAll(videoPage);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new AdapterProductItem(recyclerView, videoList, getActivity());
        recyclerView.setAdapter(contactAdapter);

        //set load more listener for the RecyclerView adapter
//        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                if (contacts.size() <= 20) {
//                    contacts.add(null);
//                    contactAdapter.notifyItemInserted(contacts.size() - 1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            contacts.remove(contacts.size() - 1);
//                            contactAdapter.notifyItemRemoved(contacts.size());
//
//                            //Generating more data
//                            int index = contacts.size();
//                            int end = index + 10;
//                            for (int i = index; i < end; i++) {
//                                Contact contact = new Contact();
//                                contact.setPhone(phoneNumberGenerating());
//                                contact.setEmail("DevExchanges" + i + "@gmail.com");
//                                contacts.add(contact);
//                            }
//                            contactAdapter.notifyDataSetChanged();
//                            contactAdapter.setLoaded();
//                        }
//                    }, 5000);
//                } else {
//                    Toast.makeText(getActivity(), "Loading data completed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page) {
                videoPage++;
                getVideoAll(page);
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
                    contactAdapter.notifyItemRangeInserted(contactAdapter.getItemCount(), videoList.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
