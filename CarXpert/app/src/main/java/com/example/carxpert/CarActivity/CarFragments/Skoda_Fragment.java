package com.example.carxpert.CarActivity.CarFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carxpert.CarActivity.CarAdapter.SkodaAdapter;
import com.example.carxpert.CarActivity.CarModel.SkodaModel;
import com.example.carxpert.R;

import java.util.ArrayList;


public class Skoda_Fragment extends Fragment {

    RecyclerView skoda_recyclerview;
    ArrayList<SkodaModel> skoda_list;
    SkodaAdapter skodaAdapter;


    public Skoda_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skoda_, container, false);


        skoda_recyclerview = view.findViewById(R.id.skoda_recyclerview);
        skoda_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        skoda_list = new ArrayList<>();

        skoda_list.add(new SkodaModel(R.drawable.skoda_kushaq,"Skoda Kushaq"," Price : 18. 30 Lakhs"," June 23 , 2021"));
        skoda_list.add(new SkodaModel(R.drawable.skoda_elroq,"Skoda Elroq"," Price : 25.00 Lakhs"," Fabuary , 2026"));
        skoda_list.add(new SkodaModel(R.drawable.skoda_slavia,"Skoda Slavia"," Price : 17. 70 Lakhs"," February 28 , 2022"));
        skoda_list.add(new SkodaModel(R.drawable.skoda_kodiak,"Skoda Kodiak"," Price : 39. 99 - 45. 96 Lakhs"," April 17 , 2025"));
        skoda_list.add(new SkodaModel(R.drawable.skoda_octavia,"Skoda Octavia"," Price : 27.35 - 30.45  Lakhs"," October 17 , 2025"));
        skoda_list.add(new SkodaModel(R.drawable.skoda_superb,"Skoda Superb"," Price : 55.00 Lakhs"," September , 2025"));


        skodaAdapter = new SkodaAdapter( getActivity(),skoda_list);
        skoda_recyclerview.setAdapter(skodaAdapter);


        return  view;
    }
    public void FilterSkodaData(String text){
        skodaAdapter.FilterSkoda(text);
    }

}