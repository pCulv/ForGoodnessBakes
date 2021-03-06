package com.example.phil.forgoodnessbakes.models;

import android.os.Parcel;
import android.os.Parcelable;



public class RecipeModel implements Parcelable
{
    private Ingredient[] ingredients;

    private String id;

    private String servings;

    private String name;

    private String image;

    private Step[] steps;

    public RecipeModel() {
    }

    public RecipeModel(Parcel in) {
        id = in.readString();
        servings = in.readString();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    public Ingredient[] getIngredients ()
    {
        return ingredients;
    }

    public void setIngredients (Ingredient[] ingredients)
    {
        this.ingredients = ingredients;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getServings ()
    {
        return servings;
    }

    public void setServings (String servings)
    {
        this.servings = servings;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public Step[] getSteps ()
    {
        return steps;
    }

    public void setSteps (Step[] steps)
    {
        this.steps = steps;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ingredients = "+ingredients+", id = "+id+", servings = "+servings+", name = "+name+", image = "+image+", steps = "+steps+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(servings);
        dest.writeString(name);
        dest.writeString(image);
    }
}