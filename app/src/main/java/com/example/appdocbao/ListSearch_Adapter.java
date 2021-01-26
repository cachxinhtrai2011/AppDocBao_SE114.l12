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

public class ListSearch_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ItemNews> itemNews;
    ArrayList<ItemNews> listSearch;
    SQLHelperNews sqlHelperNews;


    public ListSearch_Adapter(Context context, ArrayList<ItemNews> itemNews) {
        this.context = context;
        this.itemNews = itemNews;
        this.listSearch = new ArrayList<>();
        listSearch.addAll(itemNews);
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

    public void filter(String query){
        itemNews.clear();
        if(query.length() == 0){
            itemNews.addAll(listSearch);
        }else {
            for (ItemNews itemNews : listSearch){
                if(itemNews.getTitle().toLowerCase().contains(query.toLowerCase())){
                    this.itemNews.add(itemNews);
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.item_news, viewGroup, false);

        RelativeLayout rt = v.findViewById(R.id.rt_lt);
        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtPubDate = v.findViewById(R.id.txtPubDate);
        ImageView imageView = v.findViewById(R.id.imgTinTuc);

        final ItemNews itemNews = this.itemNews.get(i);
        txtTitle.setText(itemNews.getTitle());
        txtPubDate.setText(itemNews.getPubDate());
        Glide.with(context).load(itemNews.getImg()).into(imageView);
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), NewsDetail.class);
                intent.putExtra("link", itemNews.getCmt());
                v.getContext().startActivity(intent);
            }
        });

        return v;
    }
}
