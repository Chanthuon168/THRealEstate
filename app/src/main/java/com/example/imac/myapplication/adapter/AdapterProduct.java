package com.example.imac.myapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imac.myapplication.DetailPagerActivity;
import com.example.imac.myapplication.ProductDetailActivity;
import com.example.imac.myapplication.R;
import com.example.imac.myapplication.model.Product;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imac on 21/11/17.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {
    private List<Product> products;
    private Activity activity;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public AdapterProduct(Activity activity, List<Product> products) {
        this.activity = activity;
        this.products = products;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String imgUrl = products.get(position).getImageUrl();
        final String img = imgUrl.replace("&amp;","&");
        Picasso.with(holder.imageView.getContext()).load(img).into(holder.imageView);
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.short_des.setText(Html.fromHtml(products.get(position).getShortDes(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.short_des.setText(Html.fromHtml(products.get(position).getShortDes()));
        }
        holder.expandableLayout.expand();
        holder.expandableLayout.collapse();
        holder.layoutMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.expandableLayout.toggle();
            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailPagerActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("product", products.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ExpandableRelativeLayout expandableLayout;
        private LinearLayout layoutMore, mainLayout;
        private TextView name, price, short_des;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            layoutMore = itemView.findViewById(R.id.layout_more);
            short_des = itemView.findViewById(R.id.short_des);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.image);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
