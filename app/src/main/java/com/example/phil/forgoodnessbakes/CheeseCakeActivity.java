package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheeseCakeActivity extends AppCompatActivity {
    @BindView(R.id.cheeseimage)
    ImageView cheeseCakeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_cake);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this)
                .load(R.drawable.originalcheesecake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(cheeseCakeImage);
    }

}
