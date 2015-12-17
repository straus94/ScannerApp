package com.example.drum.testappscaner.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.R;

import java.util.List;


/**
 * Created by drum on 16.12.2015.
 */
public class ListViewAdapter extends ArrayAdapter{

    private Context context;
    private List mItem;
    private PackageManager packageManager;

    public ListViewAdapter(Context context, int list_item, List items) {
        super(context, R.layout.list_item, items);

        this.context = context;
        this.mItem = items;
        packageManager = context.getPackageManager();
    }


    public int getCount(){
        return ((null != mItem) ? mItem.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return (null != mItem) ? (ApplicationInfo) mItem.get(position) : null;
    }
    public long getItemId(int position){
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent){

//        ViewHolder viewHolder;
        View view = convertView;

        if (null == view) {
            //расшираем макет айтема
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        ApplicationInfo data = (ApplicationInfo) mItem.get(position);

        if (null != data){
            TextView appName = (TextView) view.findViewById(R.id.tv_app_name);
            TextView appPackage = (TextView) view.findViewById(R.id.tv_app_package);
            ImageView icon = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(data.loadLabel(packageManager));
            appPackage.setText(data.packageName);
            icon.setImageDrawable(data.loadIcon(packageManager));
        }


        return view;

            //инициализируем вьюХолдер
//            viewHolder = new ViewHolder();
//            viewHolder.icon = (ImageView) convertView.findViewById(R.id.app_icon);
//            viewHolder.appName = (TextView) convertView.findViewById(R.id.tv_app_name);
//            viewHolder.appPackage = (TextView) convertView.findViewById(R.id.tv_app_package);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        //обновляем айтем
//        ListViewItem item = getItem(position);
//        viewHolder.icon.setImageDrawable(item.icon);
//        viewHolder.appName.setText(item.appName);
//        viewHolder.appPackage.setText(item.appPackage);
//
//        return convertView;
    }

//    private static class ViewHolder {
//        ImageView icon;
//        TextView appName;
//        TextView appPackage;
//    }
}
