package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class WatchedNewsActivity extends AppCompatActivity {
    ListView lvWatchedNews;
    WatchedNewsAdapter adapter;
    SQLHelperNews sqlHelperNews;
    ImageView btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched_news);
        lvWatchedNews = findViewById(R.id.lvWatchedNews);

        sqlHelperNews = new SQLHelperNews(getBaseContext());
        ArrayList<ItemNews> itemNews = sqlHelperNews.getAllNewAdvanced();
        adapter = new WatchedNewsAdapter(getBaseContext(), itemNews);
        lvWatchedNews.setAdapter(adapter);

    }
}
