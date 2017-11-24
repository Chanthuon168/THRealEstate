package com.example.imac.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.imac.myapplication.model.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private WebView webView;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        product = (Product) getIntent().getSerializableExtra("product");
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", product.getDetail(), "text/html", "UTF-8", "");
    }
}
