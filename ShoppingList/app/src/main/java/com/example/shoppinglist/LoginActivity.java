package com.example.shoppinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edt_email;
    TextInputEditText edt_password;

    AppCompatButton btn_login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                if (email.length() == 0 && password.length() == 0){
                    edt_email.setError("Please Enter a valid Password");
                    edt_password.setError("Please Enter a Valid Email Password");
                }
                else {
                    if (email.equals("babariyarutvik1013@gmail.com") && password.equals("101324")){
                        Intent i = new Intent(getApplicationContext(), ShoppingList.class);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        i.putExtra("email",email);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}