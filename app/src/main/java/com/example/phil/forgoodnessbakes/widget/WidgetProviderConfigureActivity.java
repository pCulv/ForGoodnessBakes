package com.example.phil.forgoodnessbakes.widget;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.models.Ingredient;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WidgetProviderConfigureActivity extends Activity implements
        RadioGroup.OnCheckedChangeListener {
    private static final String PREFS_NAME =
            "com.example.phil.forgoodnessbakes.widget.WidgetProvider";
    private String recipe;
    private List<Ingredient> mIngredients = new ArrayList<>();
    public String recipesUrl =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private Gson gson = new Gson();
    private Ingredient ingredientPOJO = new Ingredient();
    private String recipeJson = gson.toJson(ingredientPOJO);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.widget_config);

        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        RadioGroup radioGroupRecipe = (RadioGroup) findViewById(R.id.radio_recipe_select);
        radioGroupRecipe.setOnCheckedChangeListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        getIngredients();
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = WidgetProviderConfigureActivity.this;


            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public WidgetProviderConfigureActivity() {
        super();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonId);
        recipe = radioButton.getText().toString();
    }

    //Share Prefs for each recipe
    static void saveRecipePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();

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
                Toast.makeText(WidgetProviderConfigureActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.i("Url", response.toString());
                WidgetProviderConfigureActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray = new JSONArray(myResponse);
                            JSONObject firstRecipe = jsonArray.getJSONObject(0);
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
