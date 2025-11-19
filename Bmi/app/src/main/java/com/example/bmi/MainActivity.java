package com.example.bmi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textresult;
    EditText edt_weight;
    EditText edt_height_ft;
    EditText edt_height_inch;
    LinearLayout main;

    Button btn_bmi;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_weight = findViewById(R.id.edt_waight);
        edt_height_ft = findViewById(R.id.edt_height_ft);
        edt_height_inch = findViewById(R.id.edt_height_inch);

        textresult = findViewById(R.id.txt_result);

        btn_bmi = findViewById(R.id.btn_bmi);

        main = findViewById(R.id.main);


        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weight=Integer.parseInt( edt_weight.getText().toString());
                int height_ft = Integer.parseInt(edt_height_ft.getText().toString());
                int height_inch = Integer.parseInt( edt_height_inch.getText().toString());


                int totalIn = height_ft * 12 + height_inch;
                double totalCm = totalIn * 2.54;
                double totalM = totalCm / 100;

                double bmi = weight / (totalM * totalM);



                // condition to check the weight and height accoringly

                if ( bmi > 25){
                    textresult.setText("You are OverWeight");
                    main.setBackgroundColor(Color.RED);
                    
                } else if (bmi < 20 ) {
                    textresult.setText("You are Underweight");
                    main.setBackgroundColor(Color.YELLOW);

                }
                else {
                    textresult.setText("You are Healthy");
                    main.setBackgroundColor(Color.GREEN);
                }
            }
        });
    }


}