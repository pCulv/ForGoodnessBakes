package com.example.phil.forgoodnessbakes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.example.phil.forgoodnessbakes.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    String recipe;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_widget);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("recipe", recipe);
        editor.apply();


    }




}
