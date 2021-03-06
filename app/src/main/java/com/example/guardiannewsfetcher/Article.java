package com.example.guardiannewsfetcher;

public class Article {
    private String mArticleTitle;
    private String mSectionName;
    private String mArticleDate;
    private String mArticleAuthor;
    private String mArticleUrl;


    public Article (String vArticleTitle, String vSectionName, String vArticleAuthor, String vArticleDate, String vArticleUrl) {
        mArticleTitle = vArticleTitle;
        mSectionName = vSectionName;
        mArticleAuthor = vArticleAuthor;
        String[] dateArrStr = vArticleDate.split("T", 2);
        mArticleDate = dateArrStr[0];
        mArticleUrl = vArticleUrl;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }
    public String getSectionName() {
        return mSectionName;
    }
    public String getArticleAuthor() {
        return mArticleAuthor;
    }
    public String getArticleDate() {
        return mArticleDate;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

}
