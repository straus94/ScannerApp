package com.example.drum.testappscaner.fragment;

import android.app.ListActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


//import com.example.drum.testappscaner.ListViewItem;
import com.example.drum.testappscaner.R;
import com.example.drum.testappscaner.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by drum on 16.12.2015.
 */
public class ResultFragment extends ListFragment {

    //add null

    private PackageManager packageManager = null;
    private ListViewAdapter listViewAdapter = null;

    private List mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mItem = new ArrayList<ListViewItem>();
//        Resources resources = getResources();

//        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher),getString(R.string.one), getString(R.string.first)));
//        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher),getString(R.string.two), getString(R.string.second)));
//        mItem.add(new ListViewItem(resources.getDrawable(R.mipmap.ic_launcher), getString(R.string.three), getString(R.string.thrid)));
//
//        setListAdapter(new ListViewAdapter(getActivity(), mItem));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // getListView().setDivider(null);

        packageManager = getContext().getPackageManager();

        new LoadApplications().execute();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo applicationInfo = (ApplicationInfo) mItem.get(position);

        try{
            Intent intent = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);
            if (intent != null){
                startActivity(intent);
            }
        }catch (ActivityNotFoundException e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private List<ApplicationInfo> checkForLauncherIntent(List<ApplicationInfo> list){

        ArrayList mItem = new ArrayList();

        for(ApplicationInfo info : list) {
            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    mItem.add(info);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return mItem;
    }



//    public PackageManager getPackageManager() {
//        return packageManager;
//    }


    private class LoadApplications extends AsyncTask<Void, Void, Void>{

        private ProgressDialog progressDialog = null;

        @Override
        protected Void doInBackground(Void... params){
            mItem = checkForLauncherIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listViewAdapter = new ListViewAdapter(getActivity(), R.layout.list_item, mItem);


            return null;
        }

//    public PackageManager getPackageManager() {
//        return packageManager;
//    }

        @Override
        protected void onPostExecute(Void result){
            setListAdapter(listViewAdapter);
//            listViewAdapter.sort(new Comparator<String>() {
//                @Override
//                public int compare(String lhs, String rhs) {
//                    return lhs.compareTo(rhs);
//                }
//            });
            progressDialog.dismiss();
            super.onPostExecute(result);
        }
        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(getActivity(), null, "Loading file info...");
            super.onPreExecute();
        }
    }

}
