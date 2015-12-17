package com.example.drum.testappscaner;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

/**
 * Created by drum on 16.12.2015.
 */
public class ListViewItem extends ApplicationInfo {
    public final Drawable icon;
    public final String appName;
    public final String appPackage;

    public ListViewItem(Drawable icon, String appName, String appPackage) {
        this.icon = icon;
        this.appName = appName;
        this.appPackage = appPackage;
    }
}
