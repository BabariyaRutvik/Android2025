package com.example.carxpert.CarActivity.CarFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carxpert.CarActivity.CarAdapter.ToyotaAdapter;
import com.example.carxpert.CarActivity.CarModel.ToyotaModel;
import com.example.carxpert.R;

import java.util.ArrayList;


public class Toyota_Fragment extends Fragment {

    RecyclerView toyota_recyclerview;
    ArrayList<ToyotaModel> toyota_list;
    ToyotaAdapter toyotaAdapter;


    public Toyota_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toyota_, container, false);

        toyota_recyclerview = view.findViewById(R.id.toyota_recyclerview);
        toyota_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        toyota_list = new ArrayList<>();


        toyota_list.add(new ToyotaModel(R.drawable.toyota_fortuner,"Toyota Fortuner"," Price : 33. 65 - 48. 85 Lakhs"," August 9 , 2009"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_fortuner_legender,"Toyota Fortuner Legender"," Price : 41. 54 - 46. 75  Lakhs"," January , 2021"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_innova_hycross,"Toyota Inoova Hycross"," Price : 18. 86 - 31. 90 Lakhs"," December 22 , 2022"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_taisor,"Toyota Taisor"," Price : 7.09 - 12. 06 Lakhs"," April 3 , 2024"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_camry,"Toyota Camry"," Price : 47. 58 Lakhs"," December 11 , 2024"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_vellfire,"Toyota VellFire"," Price : 1. 20 - 1.30 Crore"," August 3 , 2023"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_glanza,"Toyota Glanza"," Price : 6. 89 Lakhs"," March 15 , 2022"));
        toyota_list.add(new ToyotaModel(R.drawable.toyota_land_crusial_300,"Toyota Land Crusial 300"," Price :  2. 16 Crore"," January , 2023"));


        toyotaAdapter = new ToyotaAdapter(getActivity(),toyota_list);
        toyota_recyclerview.setAdapter(toyotaAdapter);


        return  view;
    }
    public void FilterToyotaData(String text){
        toyotaAdapter.FilterToyota(text);
    }
}