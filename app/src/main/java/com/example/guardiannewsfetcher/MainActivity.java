package com.example.guardiannewsfetcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks <List<Article>> {

    EditText mSearchEditText;
    Button mSubmit;
    String searchString;
    ArticleAdapter articleAdapter;
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
                    Toast.makeText(MainActivity.this, searchString, Toast.LENGTH_LONG).show();
                }

            }

        );


        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(articleAdapter);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new ArticleLoader(this, searchString);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        articleAdapter.clear();
        articleAdapter.addAll(articles);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        articleAdapter.clear();
    }
}
