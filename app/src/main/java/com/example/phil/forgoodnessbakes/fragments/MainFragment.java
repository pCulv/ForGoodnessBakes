package com.example.phil.forgoodnessbakes.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phil.forgoodnessbakes.BrownieActivity;
import com.example.phil.forgoodnessbakes.CheeseCakeActivity;
import com.example.phil.forgoodnessbakes.NutellaActivity;
import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.YellowCakeActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment {

    @BindView(R.id.nutella_card_view) CardView nutellaCardView;

    @BindView(R.id.brownie_card_view) CardView brownieCardView;

    @BindView(R.id.yellow_cake_card_view) CardView yellowCakeCardView;

    @BindView(R.id.cheese_cake_card_view) CardView cheeseCakeCardView;

    @BindView(R.id.yellow_cake_image) ImageView yellowCakeImage;

    @BindView(R.id.brownies) ImageView browniesImage;

    @BindView(R.id.nutella_cake_image) ImageView nutellaCakeImage;

    @BindView(R.id.cheese_cake_image) ImageView cheeseCakeImage;


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

        // Picasso loads images for each cardView
        Picasso.with(this.getActivity())
                .load(R.drawable.nutella_pie)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(nutellaCakeImage);

        Picasso.with(this.getActivity())
                .load(R.drawable.brownies)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(browniesImage);

        Picasso.with(this.getActivity())
                .load(R.drawable.yellow_cake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(yellowCakeImage);

        Picasso.with(this.getActivity())
                .load(R.drawable.originalcheesecake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(cheeseCakeImage);


        // Click actions for each recipe cardView

        nutellaCardView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent openRecipe = new Intent(v.getContext(), NutellaActivity.class);
                startActivity(openRecipe);
            }
        });

        brownieCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRecipe = new Intent(v.getContext(), BrownieActivity.class);
                startActivity(openRecipe);
            }
        });

        yellowCakeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRecipe = new Intent(v.getContext(), YellowCakeActivity.class);
                startActivity(openRecipe);
            }
        });

        cheeseCakeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRecipe = new Intent(v.getContext(), CheeseCakeActivity.class);
                startActivity(openRecipe);
            }
        });
        return rootView;
    }

}
