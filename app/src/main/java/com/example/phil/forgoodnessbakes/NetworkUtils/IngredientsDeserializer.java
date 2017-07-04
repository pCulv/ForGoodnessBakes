package com.example.phil.forgoodnessbakes.NetworkUtils;

import com.example.phil.forgoodnessbakes.Models.Ingredient;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class IngredientsDeserializer implements JsonDeserializer<Ingredient> {
    @Override
    public Ingredient deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        JsonElement recipe = json.getAsJsonObject().get("ingredients").getAsJsonArray();

        return new Gson().fromJson(recipe, Ingredient.class);
    }
}
