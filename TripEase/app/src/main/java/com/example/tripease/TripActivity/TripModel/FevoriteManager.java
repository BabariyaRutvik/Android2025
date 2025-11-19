package com.example.tripease.TripActivity.TripModel;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FevoriteManager {
    private static final String PREF_NAME = "fevorite_places";
    private static final String KEY_LIST = "favorites";

    public static void SavedFevorite(Context context, Top10Model top10Model) {
        ArrayList<Top10Model> favorites = getFavorites(context);
        favorites.add(top10Model);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString(KEY_LIST, gson.toJson(favorites));
        editor.apply();
    }

    public static void RemoveFevorite(Context context, String id) {
        ArrayList<Top10Model> list = getFavorites(context);
        ArrayList<Top10Model> newList = new ArrayList<>();

        for (Top10Model top10Model : list) {
            if (!top10Model.getId().equals(id)) {
                newList.add(top10Model);
            }
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(KEY_LIST, gson.toJson(newList));
        editor.apply();
    }

    public static boolean isFevorite(Context context, String id) {
        ArrayList<Top10Model> list = getFavorites(context);

        for (Top10Model top10Model : list) {
            if (top10Model.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Top10Model> getFavorites(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_LIST, null);

        if (json == null) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Top10Model>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
