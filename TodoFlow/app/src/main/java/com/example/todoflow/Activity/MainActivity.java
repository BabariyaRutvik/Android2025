package com.example.todoflow.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todoflow.Activity.Adapter.TabTodoAdapter;
import com.example.todoflow.Activity.SqliteDatabseDBHelper.SharedManager;
import com.example.todoflow.Activity.TodoFragment.All_Task_Fragment;
import com.example.todoflow.Activity.TodoFragment.CompletedTask_Fragment;
import com.example.todoflow.Activity.TodoFragment.TodaysFragment;
import com.example.todoflow.R;
import com.example.todoflow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
     SharedManager sharedManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Init Shared Manager
        sharedManager = new SharedManager(this);

        // setup toolbar
        setSupportActionBar(binding.toolbarTodo);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Todo Flow");



        // setting up a viewpager with tablayout

        binding.tabTodo.setupWithViewPager(binding.viewPagerTodo);

        // setting up the viewpager

        SetUpViewPager();






    }
    private void SetUpViewPager(){
        TabTodoAdapter adapter = new TabTodoAdapter(getSupportFragmentManager());

        // getting the Title for the fragment
        adapter.addFragment(new All_Task_Fragment(),"All Task");
        adapter.addFragment(new TodaysFragment(),"Todays Task");
        adapter.addFragment(new CompletedTask_Fragment(),"Completed");

        binding.viewPagerTodo.setAdapter(adapter);






    }
    // for logout the user


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setIcon(R.drawable.logout)
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        sharedManager.LogOutSession();
                        startActivity(new Intent(MainActivity.this, SignInActivity.class));
                        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}