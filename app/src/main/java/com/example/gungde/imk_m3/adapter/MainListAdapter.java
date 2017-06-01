package com.example.gungde.imk_m3.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gungde.imk_m3.DetailActivity;
import com.example.gungde.imk_m3.R;
import com.example.gungde.imk_m3.holder.MainViewHolder;
import com.example.gungde.imk_m3.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gungdeaditya on 13/05/17.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Article> listArray;
    private Activity context;

    public MainListAdapter(List<Article> listArray, Activity context){
        this.listArray = listArray;
        this.context = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_list,parent,false);
        MainViewHolder viewHolder = new MainViewHolder(view, context, listArray);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Article post = listArray.get(position);
        holder.getTxTitle().setText(post.getTitle());
        holder.getTxMessage().setText(post.getSubTitle());
        holder.getImgArticle().setImageResource(post.getImage());
    }

    @Override
    public int getItemCount() {
        return listArray.size();
    }

    public void refill(Article post) {
        listArray.add(post);
        notifyDataSetChanged();
    }

    public void clear() {
        listArray.clear();
        notifyDataSetChanged();
    }
}
