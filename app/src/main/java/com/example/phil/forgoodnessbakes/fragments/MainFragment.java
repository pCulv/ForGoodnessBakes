package com.example.phil.forgoodnessbakes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phil.forgoodnessbakes.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment {
    @BindView(R.id.nutella_card_view) CardView nutellaCardView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final  View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

//        nutellaCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent openRecipe = new Intent(MainFragment.this, NutellaActivity.class);
//                startActivity(openRecipe);
//            }
//        });

        return rootView;
    }

}