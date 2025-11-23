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
import com.example.todoflow.R;
import com.example.todoflow.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    LoginRegDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoginRegDbHelper dbHelper = new LoginRegDbHelper(this);

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                String PhoneNumber = binding.editTextPhone.getText().toString();
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();


                // validating the text fields if empty or not.

                if (name.isEmpty() && PhoneNumber.isEmpty() && email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean Register = dbHelper.registerUser(name, PhoneNumber, email, password);
                    if (Register == true) {
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        // if user is already register then navigate to login activity

        binding.textLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }

        });



    }
}