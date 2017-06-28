package com.example.phil.forgoodnessbakes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phil.forgoodnessbakes.R;

import butterknife.ButterKnife;

/**
 * Created by phil on 6/28/17.
 */

public class NutellaFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutella, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public NutellaFragment() {}
}
