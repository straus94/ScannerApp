package com.example.drum.testappscaner.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by drum on 16.12.2015.
 */
public class ListViewAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ResolveInfo> mItem;
    private PackageManager packageManager;


    public ListViewAdapter(Context context, ArrayList<ResolveInfo> items) {


        this.context = context;
        this.mItem = items;



        packageManager = context.getPackageManager();
        Collections.sort(mItem, new ResolveInfo.DisplayNameComparator(packageManager));
    }


    public int getCount(){

        return mItem.size();
    }


    @Override
    public ResolveInfo getItem(int position) {
        return mItem.get(position);
    }

    public long getItemId(int position){
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }


        ResolveInfo app = mItem.get(position);

            TextView appName = (TextView) convertView.findViewById(R.id.tv_app_name);
            TextView appPackage = (TextView) convertView.findViewById(R.id.tv_app_package);
            ImageView icon = (ImageView) convertView.findViewById(R.id.app_icon);


        ActivityInfo activity = app.activityInfo;


        appPackage.setText(activity.applicationInfo.packageName);
        appName.setText(app.loadLabel(packageManager));
        icon.setImageDrawable(app.loadIcon(packageManager));


        return convertView;

    }


}
