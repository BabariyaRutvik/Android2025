package com.example.creditrack.CredActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.R;
import com.example.creditrack.databinding.ActivityWelcomeScreenBinding;

public class WelcomeScreen extends AppCompatActivity {
    ActivityWelcomeScreenBinding binding;
    LoanManager loanManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // setting up  shared preference
        loanManager = new LoanManager(this);


        // if user already LoggedIn then skip welcome screen and then move to the Mainactvity
        if (loanManager.isLoggedIn()){
            startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
            finish();
            return;
        }
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(i);
              // going to the sign in screen
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                // going to the sign up screen

            }
        });


    }
}