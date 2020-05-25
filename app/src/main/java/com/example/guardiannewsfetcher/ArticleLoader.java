package com.example.guardiannewsfetcher;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.guardiannewsfetcher.utils.Guardian_jsonUtils;
import com.example.guardiannewsfetcher.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ArticleLoader extends AsyncTaskLoader {

    private String mQuery;
    private static final String TAG = NetworkUtils.class.getSimpleName();

    public ArticleLoader(Context context, String query) {
        super(context);
        Log.d(TAG, "mQuery : " + mQuery);

        if (query != null) {
            mQuery = query;
        }
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Article> loadInBackground() {
        URL url = NetworkUtils.buildUrl("Test");

        try {
            String rawJSON = NetworkUtils.getResponseFromHttpUrl(url);
            Log.d(TAG, "rawJSON : " + rawJSON);

            ArrayList<Article> articles;
            articles =  Guardian_jsonUtils.getArticleResults(this, rawJSON);

            return articles;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Article> articles = new ArrayList<Article>();
//
//        articles.add(new Article("Article 1", "Business", "http://www.espn.com"));
//        articles.add(new Article("Article 2", "Sports", "http://www.espn.com"));
//        articles.add(new Article("Article 3", "Finance", "http://www.espn.com"));
//        articles.add(new Article("Article 4", "Lifestyle", "http://www.espn.com"));
//        articles.add(new Article("Article 5", "Lifestyle", "http://www.espn.com"));
//        articles.add(new Article("Article 6", "Lifestyle", "http://www.espn.com"));
//        articles.add(new Article("Article 7", "Lifestyle", "http://www.espn.com"));
//        articles.add(new Article("Article 8", "Lifestyle", "http://www.espn.com"));
//
        return articles;
    }
}
