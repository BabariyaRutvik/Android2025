package com.example.tripease.TripActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripease.R;
import com.example.tripease.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // --- Add this check to see if the user is already logged in ---
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // If the user is already logged in, go directly to MainActivity
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish(); // Finish RegisterActivity so the user can't go back to it
            return; // Stop the rest of the onCreate method from running
        }

        binding.btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                String phone = binding.edtPhone.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtEmailPassword.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Use the same SharedPreferences name as in ProfileFragment
                    SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", name);
                    editor.putString("phone", phone);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    // Add a flag to indicate the user is now logged in
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    
                    // --- Move this part inside the successful registration block ---
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish(); // Finish the activity after successful registration
                }
            }
        });

    }
}