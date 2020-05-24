package com.example.guardiannewsfetcher;

public class Article {
    private String mArticleTitle;
    private String mSectionName;
    private String mArticleUrl;


    public Article (String vArticleTitle, String vSectionName, String vArticleUrl) {
        mArticleTitle = vArticleTitle;
        mSectionName = vSectionName;
        mArticleUrl = vArticleUrl;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }
    public String getSectionName() {
        return mSectionName;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

}
