package com.example.phil.forgoodnessbakes.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will serve as our ListView adapter for our Ingredients list in the widget
 */

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Ingredient> mIngredients = new ArrayList<>();
    private String myResponse =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public ListProvider(Context mContext, Intent intent) {
        this.mContext = mContext;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {

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