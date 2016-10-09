package com.lol.lolgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolgame.Bean.VideoBean;
import com.lol.lolgame.R;
import com.lol.lolgame.utils.ImageLoader;
import com.lol.lolgame.view.MyImageView;

import java.util.List;

/**
 * Created by Talon on 2016/10/5.
 * Talon
 */

public class MyGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<VideoBean> list;

    public MyGridViewAdapter(Context context, List<VideoBean> list) {
        ImageLoader.init(context);
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_funfragment, parent, false);
            viewHolder.imageView = (MyImageView) convertView.findViewById(R.id.img_item);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VideoBean video = list.get(position);
        ImageLoader.display(viewHolder.imageView,video.getPic_url());
        viewHolder.textView.setText(video.getName());
        return convertView;
    }

    private static class ViewHolder {
        MyImageView imageView;
        TextView textView;
    }
}
