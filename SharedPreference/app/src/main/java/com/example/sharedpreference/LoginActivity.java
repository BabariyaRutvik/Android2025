package com.example.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharedpreference.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        // if user is already logged in then navigate to Profile activity
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    binding.edtEmail.setError("Please enter email");
                    binding.edtPassword.setError("Please enter password");
                }
                sharedPreferences.getString("email", "");
                sharedPreferences.getString("password","");
                if (email.equals(sharedPreferences.getString("email", "")) && password.equals(sharedPreferences.getString("password", ""))) {

                     SharedPreferences.Editor editor = sharedPreferences.edit();
                     editor.putBoolean("isLoggedIn", true);
                     editor.apply();


                     Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this, ProfileActivity.class
                    ));

                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });









    }
}