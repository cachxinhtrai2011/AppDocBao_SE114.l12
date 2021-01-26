package com.example.appdocbao;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListNews_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ItemNews> itemNews;
    SQLHelperNews sqlHelperNews;

    public ListNews_Adapter(Context context, ArrayList<ItemNews> itemNews) {
        this.context = context;
        this.itemNews = itemNews;
    }

    @Override
    public int getCount() {
        return itemNews.size();
    }

    @Override
    public Object getItem(int i) {
        return itemNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v;
        if(i == 0){
            v = inflater.inflate(R.layout.item_news_big, viewGroup, false);
        }else {
            v = inflater.inflate(R.layout.item_news, viewGroup, false);
        }

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtPubDate = v.findViewById(R.id.txtPubDate);
        ImageView imageView = v.findViewById(R.id.imgTinTuc);

        ItemNews itemNews = this.itemNews.get(i);
        txtTitle.setText(itemNews.getTitle());
        txtPubDate.setText(itemNews.getPubDate());
        Glide.with(context).load(itemNews.getImg()).into(imageView);

        sqlHelperNews = new SQLHelperNews(context);
        return v;
    }
}
