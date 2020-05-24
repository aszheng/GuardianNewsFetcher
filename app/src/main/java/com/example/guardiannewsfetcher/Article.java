package com.example.guardiannewsfetcher;

public class Article {
    private String mArticleTitle;
    private String mSectionName;


    public Article (String vArticleTitle, String vSectionName) {
        mArticleTitle = vArticleTitle;
        mSectionName = vSectionName;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }
    public String getSectionName() {
        return mSectionName;
    }

}
