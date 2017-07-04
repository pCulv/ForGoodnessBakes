package com.example.phil.forgoodnessbakes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.Models.Ingredient;
import com.example.phil.forgoodnessbakes.R;

import java.util.ArrayList;
import java.util.List;


public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private Context mContext;
    private List<Ingredient> mIngredients = new ArrayList<>();


    public IngredientsAdapter (Context context, List<Ingredient> ingredients) {
        this.mContext = context;
        this.mIngredients = ingredients;
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
        TextView quantityTextView;
        TextView measureTextView;
        TextView ingredientTextView;


        public IngredientsViewHolder(View itemView) {
            super(itemView);

            quantityTextView = (TextView) itemView.findViewById(R.id.quantity_tv);
            measureTextView = (TextView) itemView.findViewById(R.id.measure_tv);
            ingredientTextView = (TextView) itemView.findViewById(R.id.ingredient_tv);

        }
    }
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        this.mContext = viewGroup.getContext();
        int layoutIdForIngredients = R.layout.ingredients_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForIngredients, viewGroup, false);
        IngredientsViewHolder viewHolder = new IngredientsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.IngredientsViewHolder holder, int position) {

        final Ingredient ingredient = mIngredients.get(position);
        holder.quantityTextView.setText(String.valueOf(ingredient.getQuantity()));
        holder.measureTextView.setText(ingredient.getMeasure());
        holder.ingredientTextView.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {

        return mIngredients.size();
    }
}
