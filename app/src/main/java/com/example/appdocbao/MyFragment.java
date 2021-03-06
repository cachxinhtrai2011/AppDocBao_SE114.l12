package com.example.appdocbao;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyFragment extends Fragment {
    SQLHelperNews sqlHelperNews;
    ListView listView;
    ListNews_Adapter listNews_adapter;
    ArrayList<ItemNews> itemNews;
    Data data;

    public interface Data{
        void sendData(String link);
    }

    private static final String TAG = "MyFragment";

    public MyFragment(ArrayList<ItemNews> itemNews) {
        this.itemNews = itemNews;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        listView = view.findViewById(R.id.lvTinTuc);
        sqlHelperNews = new SQLHelperNews(getContext());

        listNews_adapter = new ListNews_Adapter(getContext(), itemNews);
        listView.setAdapter(listNews_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sqlHelperNews.insertNewHistory(itemNews.get(i).getTitle(), itemNews.get(i).getImg(), itemNews.get(i).getCmt(), itemNews.get(i).getPubDate());
                data.sendData(itemNews.get(i).getCmt());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Data){
            data = (Data) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        data = null;
    }
}
