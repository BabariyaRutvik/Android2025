package com.example.creditrack.CredActivity.CredFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.CredActivity.Database.LoginHelper;
import com.example.creditrack.CredActivity.MainActivity;
import com.example.creditrack.CredActivity.WelcomeScreen;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    LoanManager loanManager;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    LoginHelper loginHelper;


    NavController navcontroller;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        // Initialize LoanManager
        loanManager = new LoanManager(requireContext());
        
        // Initialize LoginHelper
        loginHelper = new LoginHelper(requireContext());

        // initialize nav controller
        navcontroller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);


        // initialize SharedPreferences
        preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = preferences.edit();




        // Method for All Functionality

        LoadUserData();
        LoadSettingsStatus();
        HandleListener();



        return binding.getRoot();
    }
    // Load User Data
    private void LoadUserData(){
        int userId = loanManager.getUser();
        if (userId != -1 && userId != 0){
            String name = loginHelper.getUserName(userId);
            binding.textUserName.setText(name);
            binding.textUserEmail.setText(loginHelper.getUserEmail(userId));

        }
    }
    // now making a method for Load a settings functionality
    private void LoadSettingsStatus(){
        // For Enabling Dark on app
        boolean isDarkModeEnabled = preferences.getBoolean("isDarkModeEnabled", false);
        binding.switchDarkMode.setChecked(isDarkModeEnabled);
        switchDarkMode(isDarkModeEnabled);

        // For Enabling Notification
        boolean isNotificationEnabled = preferences.getBoolean("isNotificationEnabled", true);
        binding.switchNotification.setChecked(isNotificationEnabled);


        // now making the Dark mode as a Black color
        AppCompatDelegate.setDefaultNightMode(isDarkModeEnabled ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);




    }
    // now handling all listener
    private void HandleListener() {

        // changing the password
        binding.cardChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navcontroller.navigate(R.id.nav_password_change);

            }
        });
        // Notification
        binding.switchNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {

            editor.putBoolean("isNotificationEnabled", isChecked);
            editor.apply();

            Toast.makeText(requireContext(), isChecked ?
                            "Notification Enabled" : "Notification Disabled",
                    Toast.LENGTH_SHORT).show();
        });
        
        // Notification Switch Listener for handling click on switch directly
        binding.switchNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The OnCheckedChangeListener handles logic
            }
        });

        // now making a dark mode logic method
        // This method handles the logic when the switch is toggled
        binding.switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
             if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeEnabled", true);
                    editor.apply();
                    switchDarkMode(true);

                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("isDarkModeEnabled", false);
                    editor.apply();
                    switchDarkMode(false);
                }
        });

        // Dark mode card click listener (toggles the switch)
        binding.cardDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.switchDarkMode.toggle(); 
            }
        });
        
        // Notification card click listener (toggles the switch)
        binding.cardNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.switchNotification.toggle();
            }
        });
        
        
        binding.cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

    }
    private void switchDarkMode( boolean enabled){
        if (enabled) {
            // Dark Mode ON
            binding.imageDarkMode.setVisibility(View.GONE);
            binding.lightMode.setVisibility(View.VISIBLE);
            
            // Set Switch Thumb and Track Color to White for Dark Mode
            binding.switchDarkMode.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white)));
            binding.switchDarkMode.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_gray))); // Or a darker gray
            
        } else {
            // Dark Mode OFF (Light Mode)
            binding.imageDarkMode.setVisibility(View.VISIBLE);
            binding.lightMode.setVisibility(View.GONE);
            
            // Set Switch Thumb and Track Color to Sky Blue for Light Mode
            binding.switchDarkMode.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.sky_blue)));
            binding.switchDarkMode.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_blue))); // Lighter blue for track
        }
    }
    
    private void Logout() {
        // For logout Functionality i am using Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Perform logout action
                    loanManager.Logout();
                    Toast.makeText(requireContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(requireContext(), WelcomeScreen.class);
                    // Clear task to prevent going back
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    requireActivity().finish();
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                
        builder.create().show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}