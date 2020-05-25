package com.example.guardiannewsfetcher;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.example.guardiannewsfetcher.utils.GuardianJsonUtils;
import com.example.guardiannewsfetcher.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ArticleLoader extends AsyncTaskLoader {

    private String mQuery;
    private static final String TAG = GuardianJsonUtils.class.getSimpleName();

    public ArticleLoader(Context context, String query) {
        super(context);
        mQuery = query;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Article> loadInBackground() {
        URL url = NetworkUtils.buildUrl(mQuery);

        try {
            String rawJSON = NetworkUtils.getResponseFromHttpUrl(url);
            ArrayList<Article> articles;
            articles =  GuardianJsonUtils.getArticleResults(this, rawJSON);

            return articles;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
