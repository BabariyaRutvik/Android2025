package com.example.checkbox_radio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyForm extends AppCompatActivity {

    EditText edt_full_name;
    EditText edt_phone_number;
    EditText edt_email;
    EditText edt_email_password;

    RadioButton radioMale,RadioFemale;
    CheckBox check_java,check_kotlin,check_python;

    Spinner cityspinner;
    Button click_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);

        edt_full_name = findViewById(R.id.edt_name);
        edt_phone_number = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);
        edt_email_password = findViewById(R.id.edt_password);

        radioMale = findViewById(R.id.radio_btn_male);
        RadioFemale = findViewById(R.id.radio_female);

        check_java = findViewById(R.id.check_java);
        check_kotlin = findViewById(R.id.check_kotlin);
        check_python = findViewById(R.id.chek_python);

        cityspinner = findViewById(R.id.spinner);

        click_btn = findViewById(R.id.btn_click);



        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_full_name.getText().toString();
                String phone_number = edt_phone_number.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_email_password.getText().toString();


                String gender = "";


                if (radioMale.isChecked()){
                    gender = radioMale.getText().toString();
                }
                if (RadioFemale.isChecked()){
                    gender = RadioFemale.getText().toString();
                }

                // getting values from the checkboxes
                String programming = "";
                if (check_java.isChecked()){
                    programming = check_java.getText().toString() + "\n";
                }
                 if (check_kotlin.isChecked()){
                    programming = check_kotlin.getText().toString() + "\n";

                }
                if(check_python.isChecked())
                {
                    programming = check_python.getText().toString()+ "\n";

                }
                 String city = cityspinner.getSelectedItem().toString();

                String details = name+ "\n" + phone_number + "\n" + email + "\n"
                        + password+ "\n" + gender + "\n" + programming + "\n";

                Toast.makeText(MyForm.this,details+ "", Toast.LENGTH_SHORT).show();


            }





        });

    }
}