package com.example.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private final static String ROOT_FRAGMENT_TAG ="root fragment tag";

    Button btnfrag_a,btn_farg_b,btnfrag_c,btnfrag_d,btnfrag_e;
    FrameLayout fram_container;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnfrag_a = findViewById(R.id.btn_frag_a);
        btn_farg_b = findViewById(R.id.btn_frag_b);
        btnfrag_c = findViewById(R.id.btn_frag_c);
        btnfrag_d = findViewById(R.id.btn_frag_d);
        btnfrag_e = findViewById(R.id.btn_frag_e);
        fram_container = findViewById(R.id.fram_container);


        loadFragment(new E_Fragment(),0);


        btnfrag_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new A_Fragment(),1);

            }
        });

        btn_farg_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new B_Fragment(),1);
            }
        });

        btnfrag_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new C_Fragment(),1);
            }
        });


        btnfrag_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new D_Fragment(),1);
            }
        });

        btnfrag_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new E_Fragment(),0);
            }
        });
    }
    private void loadFragment(Fragment fragment, int flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (flag ==0) {
            fragmentTransaction.add(R.id.fram_container, fragment);
            fragmentManager.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.addToBackStack(ROOT_FRAGMENT_TAG);

        }
        else {
            fragmentTransaction.replace(R.id.fram_container,fragment);
            fragmentTransaction.addToBackStack(null);

        }
        fragmentTransaction.commit();
    }
    }
