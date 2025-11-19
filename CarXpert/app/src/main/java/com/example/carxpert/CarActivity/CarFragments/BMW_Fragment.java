package com.example.carxpert.CarActivity.CarFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carxpert.CarActivity.CarAdapter.BMWAdapter;
import com.example.carxpert.CarActivity.CarModel.BMWModel;
import com.example.carxpert.R;

import java.util.ArrayList;


public class BMW_Fragment extends Fragment {

    RecyclerView bmwrecyclerview;
    ArrayList<BMWModel> bmwList;

    BMWAdapter adapter;


    public BMW_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_w_, container, false);

        bmwrecyclerview = view.findViewById(R.id.bmw_recyclerview);
        bmwrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        bmwList = new ArrayList<>();


        bmwList.add(new BMWModel(R.drawable.bmwx1,"BMW X1"," Price : 50.60 - 52.20 Lakhs","January 23 , 2023"));
        bmwList.add(new BMWModel(R.drawable.bmwx2,"BMW X2"," Price : 45 Lakhs"," December , 2025"));
        bmwList.add(new BMWModel(R.drawable.bmwx3,"BMW X3"," Price : 71.20 - 73.10 Lakhs","January 18 , 2025"));
        bmwList.add(new BMWModel(R.drawable.bmwx4,"BMW X4"," Price : 96.20 Lakhs","October 23 , 2023"));
        bmwList.add(new BMWModel(R.drawable.bmwx6,"BMW X6"," Price : 90.00 Lakhs","Jane 11 , 2020"));
        bmwList.add(new BMWModel(R.drawable.bmw_x7,"BMW X7","Price : 1.25 Crore","January 13 , 2023"));
        bmwList.add(new BMWModel(R.drawable.bmw_xm,"BMW XM","Price : 2.55 Crore","December 22 , 2022"));
        bmwList.add(new BMWModel(R.drawable.bmw_m8,"BMW M8","Price : 2.38 Crore","May 8 , 2020"));


        adapter = new BMWAdapter(bmwList, getActivity());
        bmwrecyclerview.setAdapter(adapter);
        return view;
    }
    public void FilterBMWData(String text){
        adapter.filterBMW(text);
    }
}