package com.example.phil.forgoodnessbakes.Models;


public class Ingredient
{
    private float quantity;
    private String measure;
    private String ingredient;

    public Ingredient(){

    }

    public Ingredient(float quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getMeasure ()
    {
        return measure;
    }

    public void setMeasure (String measure)
    {
        this.measure = measure;
    }

    public String getIngredient ()
    {
        return ingredient;
    }

    public void setIngredient (String ingredient)
    {
        this.ingredient = ingredient;
    }

    public float getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (float quantity)
    {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }


}
