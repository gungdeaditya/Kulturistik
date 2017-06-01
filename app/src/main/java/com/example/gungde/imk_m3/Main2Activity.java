package com.example.gungde.imk_m3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gungde.imk_m3.adapter.MainListAdapter;
import com.example.gungde.imk_m3.model.Article;
import com.example.gungde.imk_m3.utils.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list)
    RecyclerView mList;
    private MainListAdapter adapter;
    private ArrayList<String> titles, subtitles;
    private ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_main2);

        setToolbar();
        setRecyclerView();
        setDataOnTheList();

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

    private void setDataOnTheList() {
        prepareData();
        onAdded();
    }

    private void prepareData() {
        titles = new ArrayList<>();
        subtitles = new ArrayList<>();
        images = new ArrayList<>();
        images.add(R.drawable.bg_login);
        images.add(R.drawable.bg_splash);
        images.add(R.drawable.bg_login);
        titles.addAll(Arrays.asList(getResources().getStringArray(R.array.title_article)));
        subtitles.addAll(Arrays.asList(getResources().getStringArray(R.array.subtitle_article)));
    }


    private void onAdded() {
        for(int i = 0 ; i < titles.size() ; i++){
            Article post = new Article(images.get(i),titles.get(i),subtitles.get(i));
            adapter.refill(post);
        }
    }

}
