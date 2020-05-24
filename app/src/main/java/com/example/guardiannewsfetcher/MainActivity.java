package com.example.guardiannewsfetcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText mSearchEditText;
    Button mSubmit;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.search_et);
        mSubmit = findViewById(R.id.search_submit);

        mSubmit.setOnClickListener(
            new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    searchString = mSearchEditText.getText().toString();
                    Log.d("HELLO WORLD", searchString);
                    Toast.makeText(MainActivity.this, searchString, Toast.LENGTH_LONG).show();
                }

            }

        );

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
