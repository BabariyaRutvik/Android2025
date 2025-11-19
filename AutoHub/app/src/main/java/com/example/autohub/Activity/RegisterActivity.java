package com.example.autohub.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.example.autohub.databinding.ActivityRegisterBinding;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                String date_of_birth = binding.edtDob.getText().toString();
                String office_time = binding.edtTime.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();


                if (name.isEmpty()){
                    binding.edtName.setError("Please Enter Full Name");
                }
                else if (date_of_birth.isEmpty()){
                    binding.edtDob.setError("Please Enter Date Of Birth");
                }
                else if (office_time.isEmpty()){
                    binding.edtTime.setError("Please Enter Office Time");
                }
                else if (email.isEmpty()){
                    binding.edtEmail.setError("Please Enter a Email");
                }
                else if (password.isEmpty()){
                    binding.edtPassword.setError("Password is Required");
                }
                else {
                    if (name.equals("Babariya Rutvik")
                            && date_of_birth.equals("24-3-2000")
                            && office_time.equals("06:15")
                            && email.equals("babariyarutvik1013@gmail.com")
                            && password.equals("101324")){

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        Toast.makeText(RegisterActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Inavid Details", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        binding.edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this,
                        (view1,selectedYear,selectedMonth,selectedDay) -> {
                            selectedMonth += 1;
                            binding.edtDob.setText(selectedDay + "-" + selectedMonth + "-" +selectedYear);


                        },year,month,day
                        );
                datePickerDialog.show();
            }
        });

        binding.edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(RegisterActivity.this,
                        (view1, selectedHour,selectedMiniut) -> {

                            String formatTime = String.format("%02d:%02d",selectedHour,selectedMiniut);

                                    binding.edtTime.setText(formatTime);
                        },hour,minute,true);

                timePickerDialog.show();

            }
        });
    }
}