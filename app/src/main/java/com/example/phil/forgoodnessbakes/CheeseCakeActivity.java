package com.example.phil.forgoodnessbakes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.phil.forgoodnessbakes.fragments.CheeseCakeActivityFragment;
import com.example.phil.forgoodnessbakes.fragments.DetailFragment;
import com.example.phil.forgoodnessbakes.models.RecipeModel;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;

public class CheeseCakeActivity extends AppCompatActivity implements FragmentInterface {

    private Boolean mTabletMode = false;
    private static final String TAG = NutellaActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_cake);

        CheeseCakeActivityFragment cheeseCakeActivityFragment =
                CheeseCakeActivityFragment.newInstance(this);
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.cheese_fragment,
                cheeseCakeActivityFragment).commit();


        if(findViewById(R.id.detail_container) != null) {
            mTabletMode = true;
        }
    }
    public boolean isTablet() {
        return mTabletMode;
    }
    //    Uri elements parsed from JSON response and passed to @DetailFragment
    private void replaceFragment(Step stepModal, String videoUrl, String description,
                                 RecyclerView.ViewHolder viewHolder, RecipeModel recipeModel) {
        Bundle args = new Bundle();
        args.putParcelable(JSONKeys.KEY_STEPS, stepModal);
        args.putString(JSONKeys.KEY_VIDEO_URL, videoUrl);
        args.putString(JSONKeys.KEY_DESCRIPTION, description);
        args.putInt("position", viewHolder.getAdapterPosition());
        args.putParcelable("recipe", recipeModel);
        DetailFragment detailActivityFragment = new DetailFragment();
        detailActivityFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_container, detailActivityFragment).commit();
    }

    private void launchDetailActivity(Step stepModal, RecyclerView.ViewHolder viewHolder,
                                      RecipeModel recipeModel) {
        Intent userClick = new Intent(this, DetailActivity.class);
        userClick.putExtra(JSONKeys.KEY_DESCRIPTION, stepModal.getDescription());
        userClick.putExtra(JSONKeys.KEY_VIDEO_URL, stepModal.getVideoURL());
        userClick.putExtra(JSONKeys.KEY_THUMBNAIL_URL, stepModal.getThumbnailURL());
        userClick.putExtra("position", viewHolder.getAdapterPosition());
        userClick.putExtra("recipe", recipeModel.getSteps());
        startActivity(userClick);
    }


    @Override
    public void handleClick(Step stepModel, String videoUrl, String description,
                            RecyclerView.ViewHolder viewHolder, RecipeModel recipeModel) {
        if (isTablet()) {
            replaceFragment(stepModel, videoUrl, description, viewHolder, recipeModel);
        }else{
            launchDetailActivity(stepModel, viewHolder, recipeModel);
        }
    }
}
