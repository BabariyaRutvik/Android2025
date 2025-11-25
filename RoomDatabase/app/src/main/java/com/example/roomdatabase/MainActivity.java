package com.example.roomdatabase;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.roomdatabase.RoomDatabase.StudentViewModel;
import com.example.roomdatabase.RoomDatabase.Students;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Students> students ;
    MyAdapter adapter;
    StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // initialising ViewModel
        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        
        // initialising arraylist
        students = new ArrayList<>();

        // initialising adapter
        adapter = new MyAdapter(this, students);
        binding.studentRecyclerview.setAdapter(adapter);
        
        binding.studentRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        // Observing data from database LiveData (Auto Refresh insert/update/delete)
        viewModel.getAllStudents().observe(this, studentList ->{
            students.clear();
            students.addAll(studentList);
            adapter.notifyDataSetChanged();
        });

        // floating action button to add the data
        binding.floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });



    }
    // Opening Dialog to add the Student Data
    private void OpenDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_student_data);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);


        // Input Fields
        TextInputEditText edtName = dialog.findViewById(R.id.edtname);
        TextInputEditText edtRollNo = dialog.findViewById(R.id.edtRoll_no);
        TextInputEditText edtAge = dialog.findViewById(R.id.edtAge);
        TextInputEditText edtCourse = dialog.findViewById(R.id.edt_course);

        // Add Button
        AppCompatButton addDatabtn = dialog.findViewById(R.id.btn_add_data);

        addDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String rollNo = edtRollNo.getText().toString();
                String age = edtAge.getText().toString();
                String course = edtCourse.getText().toString();


                if (name.isEmpty() || rollNo.isEmpty() || age.isEmpty() || course.isEmpty()){
                    edtName.setError("Please Enter Name");
                    edtRollNo.setError("Please Enter Roll No");
                    edtAge.setError("Please Enter Age");
                    edtCourse.setError("Please Enter Course");
                }else {
                    //Inserting Data into room
                    Students student = new Students(name, rollNo, age, course);
                    viewModel.InsertStudent(student);
                    Toast.makeText(MainActivity.this, " Student Data Inserted", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            }

        });
        dialog.show();


    }

}
