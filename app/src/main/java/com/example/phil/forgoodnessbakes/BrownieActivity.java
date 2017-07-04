package com.example.phil.forgoodnessbakes;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phil.forgoodnessbakes.Adapters.IngredientsAdapter;
import com.example.phil.forgoodnessbakes.Adapters.StepsAdapter;
import com.example.phil.forgoodnessbakes.Models.Ingredient;
import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.NetworkUtils.InternetConnection;
import com.example.phil.forgoodnessbakes.NetworkUtils.JSONKeys;
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

public class BrownieActivity extends AppCompatActivity {

    @BindView(R.id.brownie_image) ImageView brownieImage;
    @BindView(R.id.brownie_ingredients_rv) RecyclerView brownieRecyclerView;
    @BindView(R.id.brownie_steps_rv) RecyclerView brownieStepsRV;

    IngredientsAdapter ingredientsAdapter;
    StepsAdapter stepsAdapter;
    Toolbar toolbar;
    private ArrayList<Ingredient> mIngredients = new ArrayList<>();
    private ArrayList<Step> mSteps = new ArrayList<>();
    public String recipesUrl =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private static final String TAG = NutellaActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brownie);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Picasso.with(this)
                .load(R.drawable.brownies)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(brownieImage);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        brownieRecyclerView.setLayoutManager(linearLayoutManager);
        brownieRecyclerView.setHasFixedSize(true);

        ingredientsAdapter = new IngredientsAdapter(BrownieActivity.this, mIngredients);

        brownieRecyclerView.setAdapter(ingredientsAdapter);

        if (InternetConnection.checkConnection(this)) {
            getIngredients();
        }else {
            Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();

        }


        // setup recyclerview for steps
        LinearLayoutManager stepsLayoutManager = new LinearLayoutManager(this);
        brownieStepsRV.setLayoutManager(stepsLayoutManager);
        brownieStepsRV.setHasFixedSize(true);

        stepsAdapter = new StepsAdapter(BrownieActivity.this, mSteps);
        brownieStepsRV.setAdapter(stepsAdapter);
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
                Toast.makeText(BrownieActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.i("Url", response.toString());
                BrownieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray = new JSONArray(myResponse);
                            JSONObject firstRecipe = jsonArray.getJSONObject(1);
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

}
