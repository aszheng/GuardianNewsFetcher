package com.example.guardiannewsfetcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText mSearchEditText;
    Button mSubmit;
    TextView mResultTitle;
    String searchString;
    ArticleAdapter articleAdapter;
    ListView mListView;
    TextView mErrorMessageDisplay;
    View mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listview_main);
        mSearchEditText = findViewById(R.id.search_et);
        mSubmit = findViewById(R.id.search_submit);
        mResultTitle = findViewById(R.id.result_title);
        mLoadingIndicator = findViewById(R.id.loading_indicator);

        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mListView.setEmptyView(mErrorMessageDisplay);

        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        mListView.setAdapter(articleAdapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //Hides IME and click submit upon hitting "done"
        mSearchEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mSubmit.performClick();
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        mSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick");

                        searchString = mSearchEditText.getText().toString();
                        articleAdapter.clear();

                        ConnectivityManager connMgr = (ConnectivityManager)
                                getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                        // if connected, fetch data with loaders
                        if (isConnected()) {
                            //restart loader
                            LoaderManager.getInstance(MainActivity.this)
                                    .restartLoader(1, null, MainActivity.this);

                        } else {
                            mLoadingIndicator.setVisibility(View.GONE);
                            mErrorMessageDisplay.setText("NO CONNECTION");
                        }
                    }
                }
        );

        // if connected, fetch data with loaders
        if (isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, MainActivity.this);
            //else display error message
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mErrorMessageDisplay.setText("NO CONNECTION");
        }
    }

    public Boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");

        return new ArticleLoader(this, searchString);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        Log.d(TAG, "onLoadFinished");

        mLoadingIndicator.setVisibility(View.GONE);

        //Reset result title text with search query
        if (searchString != null && !searchString.matches("")) {
            mResultTitle.setText("Results for \"" + searchString + "\"");
        } else {
            mResultTitle.setText("Top News");
        }

        articleAdapter.clear();
        articleAdapter.addAll(articles);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        Log.d(TAG, "onLoaderReset");
        articleAdapter.clear();
    }
}
