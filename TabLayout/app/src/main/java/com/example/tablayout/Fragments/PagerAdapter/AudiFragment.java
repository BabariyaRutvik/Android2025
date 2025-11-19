package com.example.tablayout.Fragments.PagerAdapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tablayout.FragmentAdapter.AudiAdapter;
import com.example.tablayout.Model.AudiModel;
import com.example.tablayout.R;

import java.util.ArrayList;


public class AudiFragment extends Fragment {

    RecyclerView recyclerViewaudi;
    ArrayList<AudiModel> audiList;


    public AudiFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audi, container, false);

        recyclerViewaudi = view.findViewById(R.id.audi_recyclerview);
        recyclerViewaudi.setLayoutManager(new LinearLayoutManager(getActivity()));

        audiList = new ArrayList<>();

        audiList.add(new AudiModel(R.drawable.audi_q3,"Audi Q3","Price : 43.07 Lakhs"));
        audiList.add(new AudiModel(R.drawable.audi_a4,"Audi A4","Price : 46.25 Lakhs"));
        audiList.add(new AudiModel(R.drawable.audi_q5,"Audi Q5","Price : 64.75 Lakhs"));
        audiList.add(new AudiModel(R.drawable.audi_q6,"Audi Q6","Price : 55 Lakhs"));
        audiList.add(new AudiModel(R.drawable.audi_q7,"Audi Q3","Price : 86.15 Lakhs"));
        audiList.add(new AudiModel(R.drawable.audi_q8,"Audi Q8","Price : 1.26 Crore"));
        audiList.add(new AudiModel(R.drawable.audi_rs7,"Audi Rs7","Price : 2.23 Crore"));
        audiList.add(new AudiModel(R.drawable.audi_s5_sportback,"Audi S5 Sportback","Price : 73.57 Lakhs"));

        AudiAdapter audiAdapter = new AudiAdapter(audiList,getActivity());
        recyclerViewaudi.setAdapter(audiAdapter);

        return  view;
    }
}