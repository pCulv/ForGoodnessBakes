package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nutella_card_view) CardView nutellaCardView;

    @BindView(R.id.brownie_card_view) CardView brownieCardView;

    @BindView(R.id.yellow_cake_card_view) CardView yellowCakeCardView;

    @BindView(R.id.cheese_cake_card_view) CardView cheeseCakeCardView;

    @BindView(R.id.yellow_cake_image) ImageView yellowCakeImage;

    @BindView(R.id.brownies) ImageView browniesImage;

    @BindView(R.id.nutella_cake_image) ImageView nutellaCakeImage;

    @BindView(R.id.cheese_cake_image) ImageView cheeseCakeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    // Picasso loads images for each cardView
        Picasso.with(this)
                .load(R.drawable.nutella_cake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(nutellaCakeImage);

        Picasso.with(this)
                .load(R.drawable.brownies)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(browniesImage);

        Picasso.with(this)
                .load(R.drawable.yellow_cake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(yellowCakeImage);

        Picasso.with(this)
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
    }


}
