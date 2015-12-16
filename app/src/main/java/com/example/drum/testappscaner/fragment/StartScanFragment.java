package com.example.drum.testappscaner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drum.testappscaner.R;

/**
 * Created by drum on 16.12.2015.
 */
public class StartScanFragment extends Fragment {

    public StartScanFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_fragment, null);
    }
}
