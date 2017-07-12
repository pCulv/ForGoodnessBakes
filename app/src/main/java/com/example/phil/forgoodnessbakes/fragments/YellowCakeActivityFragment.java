package com.example.phil.forgoodnessbakes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phil.forgoodnessbakes.Adapters.IngredientsAdapter;
import com.example.phil.forgoodnessbakes.Adapters.StepsAdapter;
import com.example.phil.forgoodnessbakes.FragmentInterface;
import com.example.phil.forgoodnessbakes.Models.Ingredient;
import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.NetworkUtils.InternetConnection;
import com.example.phil.forgoodnessbakes.NetworkUtils.JSONKeys;
import com.example.phil.forgoodnessbakes.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class YellowCakeActivityFragment extends Fragment {
    @BindView(R.id.yellow_cake_recipe_view)
    ImageView yellowCakeImage;
    @BindView(R.id.yellow_cake_ingredients_rv)
    RecyclerView yellowCakeRV;
    @BindView(R.id.yellow_cake_steps_rv) RecyclerView yellowCakeStepsRV;
    FragmentInterface listener;
    IngredientsAdapter ingredientsAdapter;
    StepsAdapter stepsAdapter;
    Toolbar toolbar;
    private ArrayList<Ingredient> mIngredients = new ArrayList<>();
    private ArrayList<Step> mSteps = new ArrayList<>();
    public String recipesUrl =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static YellowCakeActivityFragment newInstance(FragmentInterface listener) {
        YellowCakeActivityFragment mainFragment = new YellowCakeActivityFragment();
        mainFragment.listener = listener;
        return mainFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FragmentInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentInterface");
        }
    }

    public YellowCakeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yellow_cake, container, false);
        ButterKnife.bind(this, view);



        Picasso.with(this.getActivity())
                .load(R.drawable.yellow_cake)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(yellowCakeImage);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        yellowCakeRV.setLayoutManager(linearLayoutManager);
        yellowCakeRV.setHasFixedSize(true);

        ingredientsAdapter = new IngredientsAdapter(this.getActivity(), mIngredients);

        yellowCakeRV.setAdapter(ingredientsAdapter);

        if (InternetConnection.checkConnection(this.getActivity())) {
            getIngredients();
            getSteps();
        }else {
            Toast.makeText(this.getActivity(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();

        }

        // setup recyclerview for steps
        LinearLayoutManager stepsLayoutManager = new LinearLayoutManager(this.getActivity());
        yellowCakeStepsRV.setLayoutManager(stepsLayoutManager);
        yellowCakeStepsRV.setHasFixedSize(true);

        stepsAdapter = new StepsAdapter(this.getActivity(), mSteps, listener);
        yellowCakeStepsRV.setAdapter(stepsAdapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(yellowCakeStepsRV.getContext(),
                stepsLayoutManager.getOrientation());
        yellowCakeStepsRV.addItemDecoration(mDividerItemDecoration);

        return view;
    }

    private void getIngredients() {
        // parse json and retrieve ingredients.
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(recipesUrl)
                .build();
        // Asynchronous call for json data
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.i("Url", response.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray = new JSONArray(myResponse);
                            JSONObject firstRecipe = jsonArray.getJSONObject(2);
                            JSONArray ingredientList = firstRecipe.getJSONArray("ingredients");

                            for (int i = 0; i < ingredientList.length(); i++) {
                                JSONObject innerObject = ingredientList.getJSONObject(i);

                                String quantity = innerObject.getString(JSONKeys.KEY_QUANTITY);
                                String measure = innerObject.getString(JSONKeys.KEY_MEASURE);
                                String ingredientName = innerObject.getString(JSONKeys.KEY_INGREDIENT);

                                Ingredient ingredient = new Ingredient();
                                ingredient.setQuantity(Float.parseFloat(String.valueOf(quantity)));
                                ingredient.setMeasure(measure);
                                ingredient.setIngredient(ingredientName);

                                mIngredients.add(ingredient);
                                ingredientsAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
    void getSteps() {
        try {
            runSteps();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void runSteps() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(recipesUrl)
                .build();
        // Asynchronous call for json data
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.i("Url", response.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray = new JSONArray(myResponse);
                            JSONObject firstRecipe = jsonArray.getJSONObject(2);
                            JSONArray steps = firstRecipe.getJSONArray("steps");

                            for (int i = 0; i < steps.length(); i++) {
                                JSONObject innerObject = steps.getJSONObject(i);

                                String shortDescription = innerObject.getString(JSONKeys.KEY_SHORT_DESCRIPTION);
                                String description = innerObject.getString(JSONKeys.KEY_DESCRIPTION);
                                String videoUrl = innerObject.getString(JSONKeys.KEY_VIDEO_URL);
                                String thumbnailUrl = innerObject.getString(JSONKeys.KEY_THUMBNAIL_URL);

                                Step step = new Step();
                                step.setShortDescription(shortDescription);
                                step.setDescription(description);
                                step.setVideoURL(videoUrl);
                                step.setThumbnailURL(thumbnailUrl);

                                mSteps.add(step);
                                stepsAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
}
