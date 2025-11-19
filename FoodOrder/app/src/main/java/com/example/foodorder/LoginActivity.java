package com.example.foodorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edt_email,edt_pass;
    AppCompatButton btn_login;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        btn_login = findViewById(R.id.button_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email . getText().toString();
                String password = edt_pass . getText().toString();

                if (email.isEmpty()){
                    edt_email.setError("Please Enter Your Email");
                }
                else if (password.isEmpty()){
                    edt_pass.setError("Please Enter Email Password");
                }
                else {
                    if (email.equals("babariyarutvik1013@gmail.com") && password.equals("101324")){
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(i);


                    }
                    else {
                        Toast.makeText(LoginActivity.this, "InValid Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}