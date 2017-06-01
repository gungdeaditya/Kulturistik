package com.example.gungde.imk_m3.holder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gungde.imk_m3.DetailActivity;
import com.example.gungde.imk_m3.R;
import com.example.gungde.imk_m3.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gungdeaditya on 01/06/17.
 */

public class MainViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tx_title)
    TextView txTitle;
    @BindView(R.id.tx_message)
    TextView txMessage;
    @BindView(R.id.img_article)
    ImageView imgArticle;

    private Context context;
    private List<Article> listArray;

    public MainViewHolder(View view, Context context, List<Article> listArray){
        super(view);
        this.context = context;
        this.listArray = listArray;
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
