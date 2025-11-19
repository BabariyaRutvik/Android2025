package com.example.carxpert.CarActivity.CarFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carxpert.CarActivity.CarAdapter.AudiAdapter;
import com.example.carxpert.CarActivity.CarModel.AudiModel;
import com.example.carxpert.R;

import java.util.ArrayList;


public class Audi_Fragment extends Fragment {

    RecyclerView audi_recyclerview;
    ArrayList<AudiModel> audiList;
    AudiAdapter audiAdapter;



    public Audi_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audi_, container, false);


        audi_recyclerview = view.findViewById(R.id.audi_recyclerview);
        audi_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        audiList = new ArrayList<>();

        audiList.add(new AudiModel(R.drawable.audi_q3,"Audi Q3"," Price : 43.07 Lakhs","June , 2025"));
        audiList.add(new AudiModel(R.drawable.audi_a4,"Audi a3"," Price : 46.25 Lakhs","June 9 , 2025"));
        audiList.add(new AudiModel(R.drawable.audi_q5,"Audi Q5"," Price : 64.74 Lakhs","November , 2021"));
        audiList.add(new AudiModel(R.drawable.audi_q6,"Audi Q6"," Price : 85.00 Lakhs","December , 2025"));
        audiList.add(new AudiModel(R.drawable.audi_q7,"Audi Q7"," Price : 88.14 Lakhs","November 28 , 2024"));
        audiList.add(new AudiModel(R.drawable.audi_q8,"Audi Q8"," Price : 1.10 Crore","August 22, 2024"));
        audiList.add(new AudiModel(R.drawable.audi_rs7,"Audi RS7"," Price : 2.24 Crore","July 16 , 2020"));
        audiList.add(new AudiModel(R.drawable.audi_s5_sportback,"Audi S5 Sportsback"," Price : 73.57 Lakhs","March 22 , 2021"));

        audiAdapter = new AudiAdapter(audiList,getActivity());
        audi_recyclerview.setAdapter(audiAdapter);


        return view;
    }
    public void FilterAudiData(String text){
        audiAdapter.filterAudi(text);
    }
}