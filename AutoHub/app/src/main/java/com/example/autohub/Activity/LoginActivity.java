package com.example.autohub.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.autohub.R;
import com.example.autohub.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (email.isEmpty()){
                    binding.edtEmail.setError("Email is Required");

                } else if (password.isEmpty()) {
                    binding.edtPassword.setError("Email Password is Required");
                }
                else {
                    if (email.equals("babariyarutvik1013@gmail.com")
                         && password.equals("101324")){
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(i);

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}