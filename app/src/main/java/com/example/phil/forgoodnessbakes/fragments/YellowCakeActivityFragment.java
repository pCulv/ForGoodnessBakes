package com.example.phil.forgoodnessbakes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phil.forgoodnessbakes.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class YellowCakeActivityFragment extends Fragment {



    public YellowCakeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_yellow_cake, container, false);
    }
}
