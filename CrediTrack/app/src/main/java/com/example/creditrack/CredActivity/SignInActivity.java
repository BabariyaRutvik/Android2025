package com.example.creditrack.CredActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.CredActivity.Database.LoginHelper;
import com.example.creditrack.R;
import com.example.creditrack.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    LoginHelper loginHelper;
    LoanManager loanManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // initializing database
        loginHelper = new LoginHelper(this);
        // initializing shared preference class
        loanManager = new LoanManager(this);


        // login
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                // now validating all fields
                if (email.isEmpty() || password.isEmpty()) {
                    binding.edtEmail.setError("Please Enter Email");
                    binding.edtPassword.setError("Please Enter Password");
                } else {
                    boolean isLogin = loginHelper.SignIn(email, password);
                    if (isLogin) {
                        int id = loginHelper.getUserId(email, password);
                        loanManager.Login(id);
                        Toast.makeText(SignInActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // if user hve not register yet so register from here
        binding.textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });


    }
}