package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent() != null)  {

            Step stepModal = getIntent().getParcelableExtra("step");
            String videoUrl = getIntent().getStringExtra(JSONKeys.KEY_VIDEO_URL);
            String description = getIntent().getStringExtra(JSONKeys.KEY_DESCRIPTION);
            ArrayList<Step> steps = getIntent().getParcelableArrayListExtra("steps");
            int position = getIntent().getIntExtra("position", 0);

            Bundle args = new Bundle();
            args.putParcelable(JSONKeys.KEY_STEPS, stepModal);
            args.putString(JSONKeys.KEY_VIDEO_URL, videoUrl);
            args.putString(JSONKeys.KEY_DESCRIPTION, description);
            args.putParcelableArrayList("steps", steps);
            args.putInt("position", position);

            DetailFragment detailActivityFragment = new DetailFragment();
            detailActivityFragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, detailActivityFragment).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_step_details, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent back = new Intent(this, MainActivity.class);
                startActivity(back);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
