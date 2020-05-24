package com.example.guardiannewsfetcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Article> articles = new ArrayList<Article>();

        articles.add(new Article("Article 1", "Business", "http://www.espn.com"));
        articles.add(new Article("Article 2", "Sports", "http://www.espn.com"));
        articles.add(new Article("Article 3", "Finance", "http://www.espn.com"));
        articles.add(new Article("Article 4", "Lifestyle", "http://www.espn.com"));
        articles.add(new Article("Article 5", "Lifestyle", "http://www.espn.com"));
        articles.add(new Article("Article 6", "Lifestyle", "http://www.espn.com"));
        articles.add(new Article("Article 7", "Lifestyle", "http://www.espn.com"));
        articles.add(new Article("Article 8", "Lifestyle", "http://www.espn.com"));

        ArticleAdapter articleAdapter = new ArticleAdapter(this, articles);

        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(articleAdapter);
    }

}
