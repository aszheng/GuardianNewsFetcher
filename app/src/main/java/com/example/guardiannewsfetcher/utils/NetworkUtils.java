package com.example.guardiannewsfetcher.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://content.guardianapis.com/search";
//    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String API_KEY = "test";

    //URI builder
    public static URL buildUrl(String searchType) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("q", "debates")
                .appendQueryParameter("api-key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Url to call: " + url);
        return url;
    }


    //boilerplate code for HTTP call
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
