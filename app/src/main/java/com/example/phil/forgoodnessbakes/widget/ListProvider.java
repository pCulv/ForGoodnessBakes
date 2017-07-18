package com.example.phil.forgoodnessbakes.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.models.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * This class will serve as our ListView adapter for our Ingredients list in the widget
 */

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private TextView recipeTitle;
    private static final String MY_KEY = "Recipe List";
    private SharedPreferences mSharedPrefs;
    private Context mContext;
    private Intent mIntent;
    private ArrayList<Ingredient> mIngredients = new ArrayList<>();
    private String myResponse =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private static final String TAG = ListProvider.class.getSimpleName();
    public ListProvider(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.mIntent = intent;
    }

    @Override
    public void onCreate() {
        int appWidgetId = mIntent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);



        //retrieve shared prefs
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String dataJson = sharedPreferences.getString(MY_KEY, "");

        Gson gson = new Gson();
        final ArrayList<Ingredient> ingredientArrayList = gson.fromJson(dataJson, new TypeToken<ArrayList<Ingredient>>() {
        }.getType());

        String[] ingredients = new String[ingredientArrayList.size()];

        for (int i=0; i<ingredients.length; i++) {
            Ingredient ingredientObj = ingredientArrayList.get(i);
            ingredients[i] = String.valueOf(ingredientObj.getQuantity());
            ingredients[i] = ingredientObj.getMeasure();
            ingredients[i] = ingredientObj.getIngredient();
        }

//        for (int i = 0; i < 10; i++) {
//            Ingredient ingredient = new Ingredient();
//            ingredient.setQuantity(i);
//            ingredient.setMeasure("CUP");
//            ingredient.setIngredient("cake");
//
//            mIngredients.add(ingredient);
//
//        }
    }
    @Override
    public void onDataSetChanged() {

    }


    @Override
    public void onDestroy() {
        mIngredients.clear();
    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {final RemoteViews remoteView = new RemoteViews(
            mContext.getPackageName(), R.layout.ingredients_list_item);

        Ingredient ingredient = mIngredients.get(position);

        remoteView.setTextViewText(R.id.quantity_tv, String.valueOf(ingredient.getQuantity()));
        remoteView.setTextViewText(R.id.measure_tv, ingredient.getMeasure());
        remoteView.setTextViewText(R.id.ingredient_tv, ingredient.getIngredient());
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }




}