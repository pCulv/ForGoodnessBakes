package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);



        if (getIntent() != null)  {

            Step stepModal = getIntent().getParcelableExtra("step");
            String videoUrl = getIntent().getStringExtra(JSONKeys.KEY_VIDEO_URL);
            String description = getIntent().getStringExtra(JSONKeys.KEY_DESCRIPTION);
            int position = getIntent().getIntExtra("position", 0);

            Bundle args = new Bundle();
            args.putParcelable(JSONKeys.KEY_STEPS, stepModal);
            args.putString(JSONKeys.KEY_VIDEO_URL, videoUrl);
            args.putString(JSONKeys.KEY_DESCRIPTION, description);
            args.putInt("position", position);

            DetailFragment detailActivityFragment = new DetailFragment();
            detailActivityFragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, detailActivityFragment).commit();
        }
    }
}
