package com.bw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bw.qiaoxiaohui20170922.ChannelFragment;
import com.bw.qiaoxiaohui20170922.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 乔晓慧 on 2017/9/22.
 */

public class Fragment01 extends Fragment {
    private DrawerLayout drawerLayout;
    private ListView lv;
    private TabLayout tab;
    private ViewPager pager1;
    private String[] channels = {"数据新闻", "快讯", "头条", "精编公告", "美股", "港股", "基金", "理财"};
    private String[] urls = {
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=1",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=2",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=3",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=4",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=5",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=6",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=7",
            "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10&page=8"};
    private List<ChannelFragment> mViewList = new ArrayList<>();//页卡视图集合

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //引入视图
        View view = inflater.inflate(R.layout.fragment01, null);
        //初始化组件
        lv = (ListView) view.findViewById(R.id.lv);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager1 = (ViewPager) view.findViewById(R.id.pager1);
        init();
        return view;
    }

    private void init() {
        for(int i=0;i<channels.length;i++){
            //创建栏目的fragment
            ChannelFragment fragment = new ChannelFragment();
            Bundle b = new Bundle();
            b.putString("name", channels[i]);//传递名字
            b.putString("url", urls[i]);
            fragment.setArguments(b);
            //收集fragment
            mViewList.add(fragment);
            //给tablayout添加tab选项卡
            tab.addTab(tab.newTab().setText(channels[i]));//添加tab选项卡
        }
        //getSupportFragmentManager管理者
        FragmentManager fm = getActivity().getSupportFragmentManager();
        //创建适配器
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(fm, mViewList);
        pager1.setAdapter(mAdapter);//给ViewPager设置适配器
        tab.setupWithViewPager(pager1);//将TabLayout和ViewPager关联起来。
        tab.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }


    //适配器
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<ChannelFragment> mViewList;
        public MyFragmentPagerAdapter(FragmentManager fm, List<ChannelFragment> mViewList) {
            super(fm);
            this.mViewList = mViewList;
        }
        @Override
        public Fragment getItem(int position) {
            return mViewList.get(position);
        }
        @Override
        public int getCount() {
            return mViewList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return channels[position];//页卡标题
        }
    }
}