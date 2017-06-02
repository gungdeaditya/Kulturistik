package com.example.gungde.imk_m3.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gungde.imk_m3.DetailActivity;
import com.example.gungde.imk_m3.R;
import com.example.gungde.imk_m3.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gungdeaditya on 13/05/17.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> {

    private List<Article> listArray;
    private Activity context;

    public MainListAdapter(List<Article> listArray, Activity context){
        this.listArray = listArray;
        this.context = context;
    }

    @Override
    public MainListAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_list,parent,false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainListAdapter.MainViewHolder holder, int position) {
        Article post = listArray.get(position);
        holder.getTxTitle().setText(post.getTitle().getRendered());
        holder.getTxMessage().setText(Html.fromHtml(post.getExcerpt().getRendered()));
        if(post.getImages()!=null) {
            Glide.with(context).load(post.getImages()).into(holder.getImgArticle());
        }else{
            holder.getImgArticle().setImageResource(R.drawable.bg_login);
        }
    }

    @Override
    public int getItemCount() {
        return listArray.size();
    }

    public void refill(Article post) {
        listArray.add(post);
        notifyDataSetChanged();
    }

    public void setFilter(List<Article> newList){
        listArray = new ArrayList<>();
        listArray.addAll(newList);
        notifyDataSetChanged();
    }

    public void clear() {
        listArray.clear();
    }

    public class MainViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tx_title)
        TextView txTitle;
        @BindView(R.id.tx_message)
        TextView txMessage;
        @BindView(R.id.img_article)
        ImageView imgArticle;

        public MainViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Article post = listArray.get(getLayoutPosition());
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra(context.getString(R.string.key_send_article), post);
            context.startActivity(i);
        }

        public TextView getTxTitle() {
            return txTitle;
        }

        public TextView getTxMessage() {
            return txMessage;
        }

        public ImageView getImgArticle() {
            return imgArticle;
        }
    }
}
