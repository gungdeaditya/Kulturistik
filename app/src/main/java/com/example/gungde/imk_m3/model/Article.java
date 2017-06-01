package com.example.gungde.imk_m3.model;

import java.io.Serializable;

/**
 * Created by gungdeaditya on 31/05/17.
 */

public class Article implements Serializable {

    private int image;
    private String title,subTitle,detailMessage;

    public Article(Integer image,String title,String subTitle){
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
