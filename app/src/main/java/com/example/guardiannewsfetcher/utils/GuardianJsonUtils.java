package com.example.guardiannewsfetcher.utils;

import android.util.Log;

import com.example.guardiannewsfetcher.Article;
import com.example.guardiannewsfetcher.ArticleLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuardianJsonUtils {
    private static final String TAG = GuardianJsonUtils.class.getSimpleName();

    public static ArrayList<Article> getArticleResults(ArticleLoader context, String fullJsonStr) throws JSONException {

        //recreate JSON object
        JSONObject articleResultsJson = new JSONObject(fullJsonStr);
        JSONObject fullJsonObj = articleResultsJson.getJSONObject("response");
        JSONArray resultsArr = fullJsonObj.getJSONArray("results");

        ArrayList<Article> articles = new ArrayList<Article>();

        for (int i = 0; i < resultsArr.length(); i++) {
            JSONObject articleDetail = resultsArr.getJSONObject(i);
            String strArticleTitle = articleDetail.getString("webTitle");
            String strSectionName = articleDetail.getString("sectionName");
            String strArticleDate = articleDetail.getString("webPublicationDate");
            String strWebUrl = articleDetail.getString("webUrl");

            //retrieve author inside nested TAG JSONarray if available
            String strArticleAuthor = "Author n.a"; //default if n.a.
            String tagStringifyArr = articleDetail.getString("tags");
            JSONArray tagJSONArr = new JSONArray(tagStringifyArr);

            if (tagJSONArr != null && tagJSONArr.length() > 0) {
                JSONObject tagJSONArrTag = tagJSONArr.getJSONObject(0);
                strArticleAuthor = tagJSONArrTag.getString("webTitle");
            }

            articles.add(new Article(strArticleTitle, strSectionName, strArticleAuthor, strArticleDate, strWebUrl));
        }

        return articles;
    }
}
