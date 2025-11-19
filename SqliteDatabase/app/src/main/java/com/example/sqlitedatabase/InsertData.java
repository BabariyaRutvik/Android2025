package com.example.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitedatabase.databinding.ActivityInsertDataBinding;

public class InsertData extends AppCompatActivity {

    ActivityInsertDataBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertDataBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbHelper = new DBHelper(this);

        binding.btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                String name = binding.editName.getText().toString();
                String age = binding.editAge.getText().toString();
                String date_of_birth = binding.editDob.getText().toString();
                String phone = binding.editPhone.getText().toString();
                String email = binding.editEmail.getText().toString();
                String password = binding.editPassword.getText().toString();

                if (name.isEmpty() || age.isEmpty() || date_of_birth.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InsertData.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Model model = new Model(id,name, age, date_of_birth, phone, email, password);
                    dbHelper.insertData(model);
                    Intent intent = new Intent(InsertData.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(InsertData.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                }



            }
        });






    }
}