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
import com.example.phil.forgoodnessbakes.Models.Ingredients;
import com.example.phil.forgoodnessbakes.Models.RecipeModel;
import com.example.phil.forgoodnessbakes.Models.Steps;
import com.example.phil.forgoodnessbakes.NetworkUtils.APIClient;
import com.example.phil.forgoodnessbakes.NetworkUtils.IngredientsDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NutellaActivity extends AppCompatActivity {

    @BindView(R.id.nutella_image) ImageView nutellaCakeImage;
    @BindView(R.id.nutella_ingredients_rv) RecyclerView ingredientsRecyclerView;
    @BindView(R.id.nutella_steps_rv) RecyclerView stepsRecyclerView;

    Toolbar toolbar;
    private IngredientsAdapter ingredientsAdapter;
    private StepsAdapter stepsAdapter;
    private ArrayList<Ingredients> mIngredients = new ArrayList<>();
    private ArrayList<Steps> mSteps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutella);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Picasso.with(this)
                .load(R.drawable.nutella_pie)
                .resize(1024,500)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(nutellaCakeImage);

        // setup recyclerview for ingredients
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
        ingredientsRecyclerView.setHasFixedSize(true);

        ingredientsAdapter = new IngredientsAdapter(NutellaActivity.this, mIngredients);

        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // retrofit query for ingredients recyclerView
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Type listType = new TypeToken<ArrayList<RecipeModel>>() {
        }.getType();

        Gson gson =
                new GsonBuilder()
                        .registerTypeAdapter(listType, new IngredientsDeserializer())
                        .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.client(httpClient.build()).build();

        // Create REST adapter which points to API endpoint
        APIClient mClient = retrofit.create(APIClient.class);

        // Fetch Popular Movies
        Call<ArrayList<Ingredients>> mCall = mClient.getIngredients();

        mCall.enqueue(new Callback<ArrayList<Ingredients>>() {

            @Override
            public void onResponse(Call<ArrayList<Ingredients>> call,
                                   Response<ArrayList<Ingredients>> response) {
                ArrayList<Ingredients> ingredients = response.body();
                ingredientsRecyclerView.setAdapter(new IngredientsAdapter(NutellaActivity.this,
                        ingredients));
                Log.i("Url", response.raw().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Ingredients>> call, Throwable t) {
                Toast.makeText(NutellaActivity.this, "ERROR NO NETWORK CONNECTION",
                        Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

        // setup recyclerview for steps
        LinearLayoutManager stepsLayoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setLayoutManager(stepsLayoutManager);
        stepsRecyclerView.setHasFixedSize(true);

        stepsAdapter = new StepsAdapter(NutellaActivity.this, mSteps);
        stepsRecyclerView.setAdapter(stepsAdapter);


    }
}
