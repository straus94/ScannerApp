package com.example.drum.testappscaner;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.example.drum.testappscaner.fragment.ResultFragment;

import java.util.Collections;

public class ListViewItem extends ApplicationInfo {
    public final Drawable icon;
    public final String appName;
    public final String appPackage;

    public ListViewItem(Drawable icon, String appName, String appPackage) {
        this.icon = icon;
        this.appName = appName;
        this.appPackage = appPackage;

        //Collections.sort(appName, new ResolveInfo.DisplayNameComparator());
    }
}
