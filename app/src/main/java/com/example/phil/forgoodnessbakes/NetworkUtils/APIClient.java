package com.example.phil.forgoodnessbakes.NetworkUtils;

import com.example.phil.forgoodnessbakes.Models.Ingredients;
import com.example.phil.forgoodnessbakes.Models.RecipeModel;
import com.example.phil.forgoodnessbakes.Models.Steps;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIClient {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<RecipeModel>> getRecipes();

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Ingredients>> getIngredients();

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Steps>> getSteps();
}
