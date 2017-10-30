package com.bw.qiaoxiaohui20170922;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bw.bean.Newslist;
import com.bw.bean.SuperClass;
import com.bw.utils.NetWork;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 乔晓慧 on 2017/9/22.
 */

public class ChannelFragment extends Fragment implements XListView.IXListViewListener {
    private DrawerLayout drawerLayout;
    private String name;
    private String news_url;
    private XListView xlistView;
    private ListView lv;
    private List<Newslist> list;
    private NetWork netWork = new NetWork();
    private MyBaseAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        name = (String) bundle.get("name");
        news_url = (String) bundle.get("url");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item, null);
        xlistView = (XListView) view.findViewById(R.id.xlistView);
        lv = (ListView) view.findViewById(R.id.lv);
        xlistView.setPullLoadEnable(true);
        xlistView.setXListViewListener(this);
        xlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.openDrawer(lv);
            }
        });
//        drawerLayout.closeDrawer(lv);

        init();
        return view;
    }

    private void init() {
        //通过url进行网络请求，解析json,多条目展示在ListView中
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                String json = netWork.getStringJson(news_url);
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                SuperClass superClass = new Gson().fromJson(s, SuperClass.class);
                list = superClass.getNewslist();
                adapter = new MyBaseAdapter(getActivity(),list);
                xlistView.setAdapter(adapter);
            }
        }.execute();
    }
    //设置停止的方法
    public void onLoad(){
        xlistView.stopRefresh();
        xlistView.stopLoadMore();
        xlistView.setRefreshTime("刚刚");
    }

    int j = 1;
    //下拉刷新
    @Override
    public void onRefresh() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                String json = netWork.getStringJson(news_url+j);
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                SuperClass superClass = new Gson().fromJson(s, SuperClass.class);
                list = superClass.getNewslist();
                adapter = new MyBaseAdapter(getActivity(),list);
                xlistView.setAdapter(adapter);
                onLoad();
            }
        }.execute();
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        j++;
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                String json = netWork.getStringJson(news_url+j);
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                SuperClass superClass = new Gson().fromJson(s, SuperClass.class);
                List<Newslist> newslist = superClass.getNewslist();
                list.addAll(newslist);
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }.execute();
    }

}
