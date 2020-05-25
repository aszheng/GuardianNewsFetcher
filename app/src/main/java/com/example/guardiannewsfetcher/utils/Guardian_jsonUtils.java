package com.example.guardiannewsfetcher.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.guardiannewsfetcher.Article;
import com.example.guardiannewsfetcher.ArticleLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class Guardian_jsonUtils {
    private static final String TAG = Guardian_jsonUtils.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Article> getArticleResults (ArticleLoader context, String fullJsonStr) throws JSONException, ParseException {

        //recreate JSON object
        JSONObject articleResultsJson = new JSONObject(fullJsonStr);
        JSONObject fullJsonObj = articleResultsJson.getJSONObject("response");
        JSONArray resultsArr = fullJsonObj.getJSONArray("results");

        ArrayList<Article> articles = new ArrayList<Article>();

        for (int i=0; i< resultsArr.length(); i++) {
            JSONObject articleDetail = resultsArr.getJSONObject(i);
            String strArticleTitle = articleDetail.getString("webTitle");
            String strSectionName= articleDetail.getString("sectionName");
            String strArticleDate = articleDetail.getString("webPublicationDate");
            String strWebUrl = articleDetail.getString("webUrl");

            articles.add(new Article(strArticleTitle, strSectionName, strArticleDate, strWebUrl));
        }

        return articles;
    }

}
