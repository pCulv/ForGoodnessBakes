package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.phil.forgoodnessbakes.fragments.BrownieActivityFragment;
import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class BrownieActivity extends AppCompatActivity implements FragmentInterface {

    private Boolean mTabletMode = false;
    Toolbar toolbar;
    private static final String TAG = NutellaActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brownie);
        ButterKnife.bind(this);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        BrownieActivityFragment brownieActivityFragment = BrownieActivityFragment.newInstance(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.brownie_fragment, brownieActivityFragment).commit();

        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
        }
    } public boolean isTablet() {
        return mTabletMode;
    }
    //    Uri elements parsed from JSON response and passed to @DetailFragment
    private void replaceFragment(Step stepModal, String videoUrl, String description,
                                 RecyclerView.ViewHolder viewHolder, ArrayList<Step> steps) {
        Bundle args = new Bundle();
        args.putParcelable(JSONKeys.KEY_STEPS, stepModal);
        args.putString(JSONKeys.KEY_VIDEO_URL, videoUrl);
        args.putString(JSONKeys.KEY_DESCRIPTION, description);
        args.putInt("position", viewHolder.getAdapterPosition());
        args.putParcelableArrayList("steps", steps);
        DetailFragment detailActivityFragment = new DetailFragment();
        detailActivityFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_container, detailActivityFragment).commit();
    }

    private void launchDetailActivity(Step stepModal, RecyclerView.ViewHolder viewHolder,
                                      ArrayList<Step> steps) {
        Intent userClick = new Intent(this, DetailActivity.class);
        userClick.putExtra(JSONKeys.KEY_DESCRIPTION, stepModal.getDescription());
        userClick.putExtra(JSONKeys.KEY_VIDEO_URL, stepModal.getVideoURL());
        userClick.putExtra(JSONKeys.KEY_THUMBNAIL_URL, stepModal.getThumbnailURL());
        userClick.putExtra("position", viewHolder.getAdapterPosition());
        userClick.putExtra("steps", steps);
        startActivity(userClick);
    }


    @Override
    public void handleClick(Step stepModel, String videoUrl, String description,
                            RecyclerView.ViewHolder viewHolder, ArrayList<Step> steps) {
        if (isTablet()) {
            replaceFragment(stepModel, videoUrl, description, viewHolder, steps);
        }else{
            launchDetailActivity(stepModel, viewHolder, steps);
        }
    }
}
