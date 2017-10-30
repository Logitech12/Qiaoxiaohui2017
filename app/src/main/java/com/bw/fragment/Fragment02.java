package com.bw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.qiaoxiaohui20170922.R;

/**
 * Created by 乔晓慧 on 2017/9/22.
 * Fragment2
 */

public class Fragment02 extends Fragment {
    @Nullable
    @Override
    //创建视图
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //引入视图
        View view = inflater.inflate(R.layout.fragment02, null);
        return view;
    }
}
