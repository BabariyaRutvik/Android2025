package com.example.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedpreference.databinding.ActivityRegiSterBinding;

public class Register_activity extends AppCompatActivity {

    ActivityRegiSterBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegiSterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        // If already logged in → go to Profile
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(Register_activity.this, ProfileActivity.class));
            finish();
            return;
        }

        binding.btnRegister.setOnClickListener(v -> {

            String name = binding.edtName.getText().toString();
            String email = binding.edtEmail.getText().toString();
            String password = binding.edtPassword.getText().toString();
            String phone = binding.edtPhoneNumber.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // SAVE USER REGISTRATION DATA (ONLY ONCE)
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.putBoolean("isLoggedIn", false); // IMPORTANT!
            editor.apply();

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

            // Go to LOGIN page
            Intent intent = new Intent(Register_activity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
