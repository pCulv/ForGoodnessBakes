package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.phil.forgoodnessbakes.Models.Step;

import butterknife.ButterKnife;

public class NutellaActivity extends AppCompatActivity implements FragmentInterface {
    Toolbar toolbar;
    private Boolean mTabletMode;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutella);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
            Toast.makeText(this, "You're in tablet mode!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void replaceFragment(Step stepModel) {

    }
}
