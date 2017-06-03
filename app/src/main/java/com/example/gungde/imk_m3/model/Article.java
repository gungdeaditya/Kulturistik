package com.example.gungde.imk_m3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gungdeaditya on 31/05/17.
 */

public class Article implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private Title title;
    @SerializedName("content")
    private Content content;
    @SerializedName("excerpt")
    private Excerpt excerpt;
    private String images;

    public Article() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Excerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public static class Content implements Serializable {

        @SerializedName("rendered")
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }

    public static class Excerpt implements Serializable {

        @SerializedName("rendered")
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }

    public static class Title implements Serializable {

        @SerializedName("rendered")
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }
}
