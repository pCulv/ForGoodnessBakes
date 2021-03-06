package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.fragments.NutellaFragment;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutellaActivity extends AppCompatActivity implements FragmentInterface {

    private Boolean mTabletMode = false;
    @Nullable @BindView(R.id.nutella_scroll)
    NestedScrollView mScrollView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutella);
        ButterKnife.bind(this);


        NutellaFragment nutellaFragment = NutellaFragment.newInstance(this);
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.nutella_container, nutellaFragment).commit();


        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
        }
    }



    public boolean isTablet() {
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
