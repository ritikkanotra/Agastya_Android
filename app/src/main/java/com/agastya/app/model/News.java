package com.agastya.app.model;

public class News {

    private String imageUrl;
    private String url;
    private String title;

    public News(String imageUrl, String url, String title) {
        this.imageUrl = imageUrl;
        this.url = url;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
