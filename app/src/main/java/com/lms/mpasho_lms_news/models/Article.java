package com.lms.mpasho_lms_news.models;


public class Article {

    private final String source, author, title, description, url, urlToImage, publishedAt;

    public Article(String source, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }
}
