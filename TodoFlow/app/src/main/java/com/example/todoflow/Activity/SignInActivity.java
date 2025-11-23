package com.example.todoflow.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoflow.Activity.SqliteDatabseDBHelper.LoginRegDbHelper;
import com.example.todoflow.Activity.SqliteDatabseDBHelper.SharedManager;
import com.example.todoflow.R;
import com.example.todoflow.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    LoginRegDbHelper loginRegDbHelper;
    SharedManager sharedManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        //Initialise Database
        loginRegDbHelper = new LoginRegDbHelper(this);
        //Initialise Shared Manager
        sharedManager = new SharedManager(this);




        // Login
        if (sharedManager.isLoggedIn()){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }

        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();

                // validating fields
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean isValid = loginRegDbHelper.loginUser(email, password);
                    if (isValid == true){
                       int id = loginRegDbHelper.getUserId(email);
                        sharedManager.LogInSession(id);
                        Toast.makeText(SignInActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        // if user have not account then go to register Activity

       binding.textSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
               startActivity(intent);
           }
       });

    }
}