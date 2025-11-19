package com.example.carxpert.CarActivity.CarFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carxpert.CarActivity.CarAdapter.MahindraAdapter;
import com.example.carxpert.CarActivity.CarModel.MahindraModel;
import com.example.carxpert.R;

import java.util.ArrayList;


public class Mahindra_Fragment extends Fragment {

   RecyclerView mahindra_recyclerview;
   ArrayList<MahindraModel> mahindra_list;
   MahindraAdapter mahindraAdapter;

    public Mahindra_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mahindra_, container, false);

        mahindra_recyclerview = view.findViewById(R.id.mahindra_recyclerview);
        mahindra_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        mahindra_list = new ArrayList<>();


        mahindra_list.add(new MahindraModel(R.drawable.mahindra_be_6,"Mahindra Be 6e"," Price : 18. 90 Lakhs"," November 26 , 2024"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xev_9e,"Mahindra Xev 9e"," Price : 21. 90 - 31.25 Lakhs"," November 26 , 2024"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xuv_700,"Mahindra XUV 700"," Price : 13.60 Lakhs"," August 14 , 2021"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xuv_300,"Mahindra XUV 300"," Price : 12.00 Lakhs"," Fabuary 19 , 2019"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xuv_200,"Mahindra XUV 200"," Price : 14.40 Lakhs"," November 26 , 2018"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_scoriio_n,"Mahindra Scorpio n"," Price : 24.67 Lakhs"," June 27 , 2024"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xuv_500,"Mahindra XUV 500"," Price : 14. 23 Lakhs"," November , 2021"));
        mahindra_list.add(new MahindraModel(R.drawable.mahindra_xuv_400_ev,"Mahindra XUV 400 ev"," Price : 15.90 Lakhs"," January , 2023"));


        mahindraAdapter = new MahindraAdapter(getActivity(), mahindra_list);
        mahindra_recyclerview.setAdapter(mahindraAdapter);


        return  view;
    }
    public void FilterMahindraData(String text){
        mahindraAdapter.FilterMahindra(text);
    }
}