package com.lms.mpasho_lms_news.models;


public class News {


    private final String status, article;
    private final Integer totalResult;

    public News(String status, String article, Integer totalResult) {
        this.status = status;
        this.article = article;
        this.totalResult = totalResult;
    }
}
