package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.fragments.DetailActivityFragment;

import butterknife.ButterKnife;

public class StepDetailActivity extends AppCompatActivity implements FragmentInterface {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



    }


    @Override
    public void replaceFragment(Step stepModel) {
        DetailActivityFragment detailActivityFragment = (DetailActivityFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_container);

        if (detailActivityFragment != null) {
            detailActivityFragment.updateDetailView();

        }
    }
}
