package com.example.guardiannewsfetcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    private Context context;

    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Article currentArticle = getItem(position);
        final String articleTitle = currentArticle.getArticleTitle();
        final String sectionName = currentArticle.getSectionName();
        final String articleAuthor = currentArticle.getArticleAuthor();
        final String articleDate = currentArticle.getArticleDate();
        final String articleUrl = currentArticle.getArticleUrl();

        LinearLayout articleListItem_layout = (LinearLayout) listItemView.findViewById(R.id.article_list_item_tv);
        articleListItem_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(articleUrl);
            }
        });

        TextView articleTitle_tv = (TextView) listItemView.findViewById(R.id.article_title_tv);
        articleTitle_tv.setText(articleTitle);

        TextView articleSection_tv = (TextView) listItemView.findViewById(R.id.article_section_tv);
        articleSection_tv.setText(sectionName);

        TextView articleAuthor_tv = (TextView) listItemView.findViewById(R.id.article_author_tv);
        articleAuthor_tv.setText(articleAuthor);

        TextView articleDate_tv = (TextView) listItemView.findViewById(R.id.article_date_tv);
        articleDate_tv.setText(articleDate);

        return listItemView;
    }

    public void openNewActivity (String articleUrl) {
        String url = articleUrl;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }


}
