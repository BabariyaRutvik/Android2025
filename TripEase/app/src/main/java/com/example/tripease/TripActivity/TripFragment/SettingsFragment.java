package com.example.tripease.TripActivity.TripFragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tripease.R;
import com.example.tripease.TripActivity.TripModel.FevoriteManager;
import com.example.tripease.TripActivity.TripModel.Top10Model;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    ToggleButton toggleButtonDarkMode,toggleButtonFavPlaces;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        toggleButtonDarkMode = view.findViewById(R.id.toggle_dark_mode);
        toggleButtonFavPlaces = view.findViewById(R.id.toggle_backupfavPlace);

        SharedPreferences preferences = getActivity().getSharedPreferences("settings",MODE_PRIVATE);

        // LOAD state
        boolean isDarkMode = preferences.getBoolean("isDarkMode",false);
        boolean isFavPlaces = preferences.getBoolean("isFavPlaces",false);


        toggleButtonDarkMode.setChecked(isDarkMode);
        toggleButtonFavPlaces.setChecked(isFavPlaces);


        // for enabling dark mode on app
        toggleButtonDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isDarkMode",isChecked);
            editor.apply();

            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getActivity().recreate();

        });

        // for enabling backup fav places
        toggleButtonFavPlaces.setOnCheckedChangeListener((buttonView, isChecked) -> {

              SharedPreferences.Editor editor = preferences.edit();
              editor.putBoolean("isFavPlaces",isChecked);
              editor.apply();

              if (isChecked){
                  // --- REAL BACKUP LOGIC STARTS HERE ---
                  // 1. Get the current list of favorites.
                  ArrayList<Top10Model> favoritesToBackup = FevoriteManager.getFavorites(getActivity());

                  // 2. Create a separate SharedPreferences file for the backup.
                  SharedPreferences backupPrefs = getActivity().getSharedPreferences("backup_prefs", MODE_PRIVATE);
                  SharedPreferences.Editor backupEditor = backupPrefs.edit();

                  // 3. Convert the list to JSON and save it.
                  Gson gson = new Gson();
                  String backupJson = gson.toJson(favoritesToBackup);
                  backupEditor.putString("favorites_backup", backupJson);
                  backupEditor.apply();

                  Toast.makeText(getActivity(), "Favorites backed up", Toast.LENGTH_SHORT).show();
              }else{
                  // --- DELETE BACKUP LOGIC STARTS HERE ---
                  // 1. Open the backup SharedPreferences file.
                   SharedPreferences backupPrefs = getActivity().getSharedPreferences("backup_prefs", MODE_PRIVATE);
                  SharedPreferences.Editor backupEditor = backupPrefs.edit();

                  // 2. Clear all data from the backup file.
                  backupEditor.clear();
                  backupEditor.apply();

                  Toast.makeText(getActivity(), "Backup disabled and cleared", Toast.LENGTH_SHORT).show();
              }

        });

      return  view;
    }
}