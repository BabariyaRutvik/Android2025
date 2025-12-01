package com.example.creditrack.CredActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.creditrack.CredActivity.Database.LoginHelper;
import com.example.creditrack.R;
import com.example.creditrack.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    LoginHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initializing database
        helper = new LoginHelper(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtFullName.getText().toString();
                String phone = binding.edtPhoneNumber.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                // validating all fields
                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    if (name.isEmpty()) binding.edtFullName.setError("Please Enter Full Name");
                    if (phone.isEmpty()) binding.edtPhoneNumber.setError("Please Enter Phone Number");
                    if (email.isEmpty()) binding.edtEmail.setError("Please Enter Email");
                    if (password.isEmpty()) binding.edtPassword.setError("Please Enter Password");
                } else {
                    boolean isSignUp = helper.SignUp(name, phone, email, password);
                    if (isSignUp) {
                        Toast.makeText(SignupActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // if user hve already register then sign in from here
        binding.textSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(i);
            }
        });
    }
}