package com.example.drum.testappscaner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.R;

import java.util.List;


/**
 * Created by drum on 16.12.2015.
 */
public class ListViewAdapter extends ArrayAdapter<ListViewItem> {
    public ListViewAdapter(Context context, List<ListViewItem> items) {
        super(context, R.layout.list_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder viewHolder;

        if (convertView == null){
            //расшираем макет айтема
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            //инициализируем вьюХолдер
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.appName = (TextView) convertView.findViewById(R.id.tv_app_name);
            viewHolder.appPackage = (TextView) convertView.findViewById(R.id.tv_app_package);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //обновляем айтем
        ListViewItem item = getItem(position);
        viewHolder.icon.setImageDrawable(item.icon);
        viewHolder.appName.setText(item.appName);
        viewHolder.appPackage.setText(item.appPackage);

        return convertView;
    }

    private static class ViewHolder {
        ImageView icon;
        TextView appName;
        TextView appPackage;
    }
}
