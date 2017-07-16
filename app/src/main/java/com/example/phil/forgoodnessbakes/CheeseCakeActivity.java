package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;
import com.example.phil.forgoodnessbakes.fragments.CheeseCakeActivityFragment;
import com.example.phil.forgoodnessbakes.fragments.DetailFragment;

public class CheeseCakeActivity extends AppCompatActivity implements FragmentInterface {

    private Boolean mTabletMode = false;
    private static final String TAG = NutellaActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_cake);

        CheeseCakeActivityFragment cheeseCakeActivityFragment = CheeseCakeActivityFragment.newInstance(this);
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.cheese_fragment, cheeseCakeActivityFragment).commit();


        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
        }
    }
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
