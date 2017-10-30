package com.bw.qiaoxiaohui20170922;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.bean.Newslist;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 乔晓慧 on 2017/9/22.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Newslist> list;

    public MyBaseAdapter(Context context, List<Newslist> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明优化类
        ViewHolder holder;
        //判断是否开始滑动
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.adapter,null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Newslist newslist = list.get(position);
        ImageLoader.getInstance().displayImage(newslist.getPicUrl(),holder.iv);
        holder.tv.setText(newslist.getDescription()+"\n"+newslist.getCtime()+"\n"+newslist.getTitle());
        return convertView;
    }
    //优化类
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
