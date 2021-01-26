package com.example.appdocbao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WatchedNewsAdapter extends BaseAdapter {

    Context context;
    ArrayList<ItemNews> listWatchedNews;

    public WatchedNewsAdapter(Context context, ArrayList<ItemNews> listWatchedNews) {
        this.context = context;
        this.listWatchedNews = listWatchedNews;
    }

    @Override
    public int getCount() {
        return listWatchedNews.size();
    }

    @Override
    public Object getItem(int i) {
        return listWatchedNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item_watchednews, viewGroup, false);
        RelativeLayout rts= v.findViewById(R.id.rt_dx);
        TextView txtTitle = v.findViewById(R.id.txtTitleWatchedNews);
        TextView txtPubDate = v.findViewById(R.id.txtPubDateWatchedNews);
        ImageView imgTinTuc = v.findViewById(R.id.imgWatchedNews);

        final ItemNews itemNews = listWatchedNews.get(i);

        txtTitle.setText(itemNews.getTitle());
        txtPubDate.setText(itemNews.getPubDate());
         rts.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent();
                 intent.setClass(v.getContext(), NewsDetail.class);
                 intent.putExtra("link", itemNews.getCmt());
                 v.getContext().startActivity(intent);
             }
         });
        Glide.with(context).load(itemNews.getImg()).into(imgTinTuc);
        return v;
    }
}
