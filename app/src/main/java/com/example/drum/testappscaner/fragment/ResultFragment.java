package com.example.drum.testappscaner.fragment;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


import com.example.drum.testappscaner.R;
import com.example.drum.testappscaner.adapter.ListViewAdapter;


import java.util.List;


public class ResultFragment extends ListFragment {

    private PackageManager packageManager = null;
    private ListViewAdapter listViewAdapter = null;

    private List mItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        packageManager = getContext().getPackageManager();

        LoadApplications loadApplications = new LoadApplications();
        loadApplications.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        ApplicationInfo applicationInfo = (ApplicationInfo) mItem.get(position);
//
//        try{
//            Intent intent = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);
//            if (intent != null){
//                startActivity(intent);
//            }
//        }catch (ActivityNotFoundException e){
//            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
//        }catch (Exception e){
//            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
//        }

//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo resolveInfo = listViewAdapter.getItem(position);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName,activityInfo.name);

            Intent intent = new Intent();
            intent.setComponent(name);
            startActivity(intent);
        }



    private ArrayList<ResolveInfo> checkForLauncherIntent(List<ApplicationInfo> list){

        ArrayList<ResolveInfo> mItems = new ArrayList();

        for(ApplicationInfo info : list) {
            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    Intent intent = packageManager.getLaunchIntentForPackage(info.packageName);
                    ResolveInfo app = packageManager.resolveActivity(intent, 0);
                    mItems.add(app);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return mItems;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void>{

        private ProgressDialog progressDialog = null;

        @Override
        protected Void doInBackground(Void... params){
            ArrayList<ResolveInfo> mItem = checkForLauncherIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listViewAdapter = new ListViewAdapter(getContext(), mItem);


            return null;
        }


        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            setListAdapter(listViewAdapter);
            progressDialog.dismiss();

        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
           progressDialog = ProgressDialog.show(getActivity(), null, getString(R.string.loading));

        }
    }

}
