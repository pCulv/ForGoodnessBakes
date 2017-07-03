package com.example.phil.forgoodnessbakes.NetworkUtils;

import com.example.phil.forgoodnessbakes.Models.RecipeModel;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by phil on 6/30/17.
 */

public class RecipeDeserializer implements JsonDeserializer<RecipeModel> {
    @Override
    public RecipeModel deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        JsonElement results = json.getAsJsonObject().get("ingredients").getAsJsonArray();

        return new Gson().fromJson(results, type);
    }
}
