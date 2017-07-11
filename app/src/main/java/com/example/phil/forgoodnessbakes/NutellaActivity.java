package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.NetworkUtils.JSONKeys;
import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.fragments.NutellaFragment;

import butterknife.ButterKnife;

public class NutellaActivity extends AppCompatActivity implements FragmentInterface {
    Toolbar toolbar;
    private Boolean mTabletMode = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutella);
        ButterKnife.bind(this);

        NutellaFragment nutellaFragment = NutellaFragment.newInstance(this);
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.nutella_container, nutellaFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
            DetailFragment detailActivityFragment = new DetailFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, detailActivityFragment).commit();
            Toast.makeText(this, "You're in tablet mode!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isTablet() {
        return mTabletMode;
    }

    private void replaceFragment(Step stepModal, String videoUrl, String description) {
        Bundle args = new Bundle();
        args.putParcelable(JSONKeys.KEY_STEPS, stepModal);
        args.putString(JSONKeys.KEY_VIDEO_URL, videoUrl);
        args.putString(JSONKeys.KEY_DESCRIPTION, description);
        DetailFragment detailActivityFragment = new DetailFragment();
        detailActivityFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_container, detailActivityFragment).commit();
    }

    private void launchDetailActivity(Step stepModal) {
        Intent userClick = new Intent(this, DetailActivity.class);
        userClick.putExtra(JSONKeys.KEY_DESCRIPTION, stepModal.getDescription());
        userClick.putExtra(JSONKeys.KEY_VIDEO_URL, stepModal.getVideoURL());
        userClick.putExtra(JSONKeys.KEY_THUMBNAIL_URL, stepModal.getThumbnailURL());
        startActivity(userClick);
    }


    @Override
    public void handleClick(Step stepModel, String videoUrl, String description) {
        if (isTablet()) {
            replaceFragment(stepModel, videoUrl, description);
        }else{
            launchDetailActivity(stepModel);
        }
    }
}
