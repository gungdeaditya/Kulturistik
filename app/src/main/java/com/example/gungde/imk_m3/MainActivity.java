package com.example.gungde.imk_m3;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.gungde.imk_m3.adapter.MainListAdapter;
import com.example.gungde.imk_m3.api.ApiInterface;
import com.example.gungde.imk_m3.model.Article;
import com.example.gungde.imk_m3.utils.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mySwipeRefreshLayout;
    @BindView(R.id.progress_bar_users)
    ProgressBar mProgressBarForUsers;

    private List<String> images = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_main);

        setToolbar();
        setRecyclerView();
        setSwipeRefresh();
        setImages();
    }

    private void setImages() {
        images.addAll(Arrays.asList(getResources().getStringArray(R.array.images)));
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setRecyclerView() {
        mList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainListAdapter(new ArrayList<Article>(), this);
        mList.setAdapter(adapter);
    }

    private void setSwipeRefresh() {
        mySwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorGray700));
        mySwipeRefreshLayout.setOnRefreshListener(MainActivity.this);
    }

    private void queryAllArticles () {
        ApiInterface apiInterface = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<Article>> call = apiInterface.getArticle("true");
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                clearList();
                prepareData(response.body());
                hideProgressBarForUsers();
                hideSwipeRefreshForUsers();
            }
            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.e(TAG, t.getMessage()+"");
                hideProgressBarForUsers();
                hideSwipeRefreshForUsers();
            }
        });
    }

    private void prepareData(List<Article> body) {
        mList.setVisibility(View.VISIBLE);
        for(int i = 0;i<body.size();i++) {
            body.get(i).setImages(images.get(i));
            adapter.refill(body.get(i));
            articles.add(body.get(i));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressBarForUsers();
        queryAllArticles();
    }

    @Override
    public void onRefresh() {
        queryAllArticles();
    }

    @Override
    public void onStop() {
        super.onStop();
        clearList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();

        ArrayList<Article> newArticles = new ArrayList<>();
        for(Article article : articles) {
            String title = article.getTitle().getRendered().toLowerCase();
            if(title.contains(newText)) {
                newArticles.add(article);
            }
        }
        adapter.setFilter(newArticles);
        return true;
    }

    private void showProgressBarForUsers() {
        mList.setVisibility(View.GONE);
        mProgressBarForUsers.setVisibility(View.VISIBLE);
    }

    private void hideProgressBarForUsers() {
        if (mProgressBarForUsers.getVisibility() == View.VISIBLE) {
            mProgressBarForUsers.setVisibility(View.GONE);
        }
    }

    private void hideSwipeRefreshForUsers() {
        mySwipeRefreshLayout.setRefreshing(false);
    }

    private void clearList() {
        adapter.clear();
        articles.clear();
    }

}
