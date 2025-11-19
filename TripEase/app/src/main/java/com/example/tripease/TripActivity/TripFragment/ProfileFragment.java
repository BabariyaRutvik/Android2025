package com.example.tripease.TripActivity.TripFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripease.R;
import com.example.tripease.TripActivity.RegisterActivity;
import com.example.tripease.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    // Declare the binding variable
    private FragmentProfileBinding binding;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Tell the fragment that it has an options menu.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Inflate the layout using the binding class
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        // 2. Get the root view from the binding object
        View view = binding.getRoot();

       // IMPORTANT: The SharedPreferences name must match the one used in RegisterActivity.
       SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

      binding.textName.setText(sharedPreferences.getString("name", ""));
      binding.textPhone.setText(sharedPreferences.getString("phone", ""));
      binding.textEmail.setText(sharedPreferences.getString("email", ""));
      binding.textPassword.setText(sharedPreferences.getString("password", ""));

        // 3. Return the root view
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){

            new AlertDialog.Builder(getContext())
                    .setTitle("Logout")
                    .setMessage("Are You Sure You want to logout ?")
                    .setIcon(R.drawable.logout)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        // Add a flag to indicate the user has logged out
                        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();

                        Intent intent = new Intent(requireContext(), RegisterActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 4. Set the binding to null to avoid memory leaks when the view is destroyed.
        binding = null;
    }
}