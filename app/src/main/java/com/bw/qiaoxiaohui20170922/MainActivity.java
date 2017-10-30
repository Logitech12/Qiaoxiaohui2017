package com.bw.qiaoxiaohui20170922;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.bw.fragment.Fragment01;
import com.bw.fragment.Fragment02;
import com.bw.fragment.Fragment03;
import com.bw.fragment.Fragment04;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //全局声明
    private ViewPager pager;
    private RadioGroup rg;
    private Fragment01 fragment01 = new Fragment01();
    private Fragment02 fragment02 = new Fragment02();
    private Fragment03 fragment03 = new Fragment03();
    private Fragment04 fragment04 = new Fragment04();
    private DrawerLayout drawerLayout;
    private ListView lv;
    //保存Fragment的集合
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        pager = (ViewPager) findViewById(R.id.pager);
        rg = (RadioGroup) findViewById(R.id.rg);
        //将Fragment添加到集合中
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
        list.add(fragment04);
        //创建适配器并关联
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //fragment的滑动监听
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                //滑动页面选择对应的按钮
                switch (position){
                    //选择第一个页面
                    case 0:
                        //改变对应的按钮1
                        rg.check(R.id.rb1);
                        break;
                    //选择第二个页面
                    case 1:
                        //改变对应的按钮2
                        rg.check(R.id.rb2);
                        break;
                    //选择第三个页面
                    case 2:
                        //改变对应的按钮3
                        rg.check(R.id.rb3);
                        break;
                    //选择第四个页面
                    case 3:
                        //改变对应的按钮4
                        rg.check(R.id.rb4);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //按钮点击监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //点击按钮选择对应的页面
                switch (checkedId){
                    //选择对应的按钮1
                    case R.id.rb1:
                        //改变对应的页面
                        pager.setCurrentItem(0);
                        break;
                    //选择对应的按钮2
                    case R.id.rb2:
                        //改变对应的页面
                        pager.setCurrentItem(1);
                        break;
                    //选择对应的按钮3
                    case R.id.rb3:
                        //改变对应的页面
                        pager.setCurrentItem(2);
                        break;
                    //选择对应的按钮4
                    case R.id.rb4:
                        //改变对应的页面
                        pager.setCurrentItem(3);
                        break;
                }
            }
        });
//        lv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(lv);
//            }
//        });
    }
}
