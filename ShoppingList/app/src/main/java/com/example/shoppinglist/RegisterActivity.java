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

public class RegisterActivity extends AppCompatActivity {

    /*
       Initializing the variable or textinputEditText
     */
    TextInputEditText edt_full_name;
    TextInputEditText edt_phone_number;
    TextInputEditText edt_address;
    TextInputEditText edt_email;
    TextInputEditText edt_password;

    AppCompatButton btn_register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        /*
           intializing by findviewbyid
         */
        edt_full_name = findViewById(R.id.edt_full_name);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_address = findViewById(R.id.edt_adress);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_register = findViewById(R.id.Button_register);




        /*
          Button set onclickListener
         */

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                   Validation or set error message if users will blank to the some fields
                 */

                String FullName = edt_full_name.getText().toString();
                String PhoneNumber = edt_phone_number.getText().toString();
                String address = edt_address.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();



                /*
                 Setting up a validation for registration field
                 */

                if (FullName. length() == 0 && PhoneNumber .length() == 0 &&
                        address .length() == 0 && email .length() == 0 && password . length() == 0){

                    edt_full_name.setError("Please Enter Full Name");
                    edt_phone_number.setError("Please Enter Phone Number");
                    edt_address.setError("Please enter your address");
                    edt_email.setError("Please Enter Email Adress");
                    edt_password.setError("Please Enter Email Password");


                }
                else {
                    if (FullName.equals("Rutvik Babariya") && PhoneNumber.equals("6351202084")
                    && address.equals("Ahmedabad")
                            && email.equals("babariyarutvik1013@gmail.com")
                            && password.equals("101324")){

                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        Toast.makeText(RegisterActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "InValid Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}