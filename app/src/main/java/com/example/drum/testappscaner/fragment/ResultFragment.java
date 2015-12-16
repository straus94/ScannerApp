package com.example.drum.testappscaner.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.R;
import com.example.drum.testappscaner.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drum on 16.12.2015.
 */
public class ResultFragment extends ListFragment {

//    String data[] = new String[] {"one", "two", "three", "four"};
//    TextView appName, appPackage;

    private List<ListViewItem> mItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItem = new ArrayList<ListViewItem>();
        Resources resources = getResources();

        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher),getString(R.string.one), getString(R.string.first)));
        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher),getString(R.string.two), getString(R.string.second)));
        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher), getString(R.string.three), getString(R.string.thrid)));

        setListAdapter(new ListViewAdapter(getActivity(), mItem));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListViewItem item = mItem.get(position);

        Toast.makeText(getActivity(), item.appName, Toast.LENGTH_LONG);
    }
}
