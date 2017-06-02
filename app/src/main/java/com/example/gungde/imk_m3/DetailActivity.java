package com.example.gungde.imk_m3;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gungde.imk_m3.model.Article;
import com.example.gungde.imk_m3.utils.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.img_article)
    ImageView mImgArticle;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_detail);

        setToolbar();
        prepareData();
        setRefreshLayout();
        setWebView();
    }

    private void setToolbar() {
        Article post = (Article) getIntent().getSerializableExtra(getString(R.string.key_send_article));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarLayout.setTitle(post.getTitle().getRendered());
        mToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        mToolbarLayout.setCollapsedTitleGravity(getResources().getColor(R.color.colorGray800));
    }

    private void prepareData() {
        Article post = (Article) getIntent().getSerializableExtra(getString(R.string.key_send_article));
        if(post.getImages()!=null) {
            Glide.with(this).load(post.getImages()).into(mImgArticle);
        }else{
            mImgArticle.setImageResource(R.drawable.bg_login);
        }
    }

    private void setRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setWebView() {
        webView.setWebViewClient(new CustomWebViewClient());
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);
        setUrlToLoad();
    }

    private void setUrlToLoad() {
        Article post = (Article) getIntent().getSerializableExtra(getString(R.string.key_send_article));
        webView.loadUrl(post.getLink());
    }

    @OnClick(R.id.fab)
    public void fabOnClick(View v){
        onShare();
    }

    private void onShare() {
        Article post = (Article) getIntent().getSerializableExtra(getString(R.string.key_send_article));
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, post.getTitle().getRendered()
                + "\n"
                + "Link : "
                + post.getLink());
        i.setType("text/plain");
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
