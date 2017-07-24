package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.fragments.NutellaFragment;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutellaActivity extends AppCompatActivity implements FragmentInterface {
    Toolbar toolbar;
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

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//
//        if (mScrollView != null) {
//            outState.putIntArray("SCROLL_POSITION",
//                    new int[]{ mScrollView.getScrollX(), mScrollView.getScrollY()});
//        }
//    }
////
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        final int[] position = savedInstanceState.getIntArray("SCROLL_POSITION");
//        if (position != null)
//            mScrollView.post(new Runnable() {
//                public void run() {
//                    mScrollView.scrollTo(position[0], position[1]);
//                }
//            });
//    }


    public boolean isTablet() {
        return mTabletMode;
    }
//    Uri elements parsed from JSON response and passed to @DetailFragment
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
