package com.example.guardiannewsfetcher;

public class Article {
    private String mArticleTitle;
    private String mSectionName;
    private String mArticleDate;
    private String mArticleUrl;


    public Article (String vArticleTitle, String vSectionName, String vArticleDate, String vArticleUrl) {
        mArticleTitle = vArticleTitle;
        mSectionName = vSectionName;
        mArticleDate = vArticleDate;
        mArticleUrl = vArticleUrl;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }
    public String getSectionName() {
        return mSectionName;
    }
    public String getArticleDate() {
        return mArticleDate;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

}
