package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrownieActivity extends AppCompatActivity {
    @BindView(R.id.brownie_image)
    ImageView brownieImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brownie);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this)
                .load(R.drawable.brownies)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(brownieImage);
    }

}
