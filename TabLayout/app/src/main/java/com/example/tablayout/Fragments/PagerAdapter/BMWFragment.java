package com.example.tablayout.Fragments.PagerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tablayout.FragmentAdapter.BmwAdapter;
import com.example.tablayout.Model.BmwModel;
import com.example.tablayout.R;

import java.util.ArrayList;


public class BMWFragment extends Fragment {

    RecyclerView bmwrecyclerview;
    ArrayList<BmwModel> bmwlist;


    public BMWFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_w, container, false);

        bmwrecyclerview = view.findViewById(R.id.bmw_recyclerview);
        bmwrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        bmwlist = new ArrayList<>();

        bmwlist.add(new BmwModel(R.drawable.bmw_x7,"BMW X7"," Price: 1.20 Crore"));
        bmwlist.add(new BmwModel(R.drawable.bmwx2,"BMW X2"," Price: 45 Lakhs"));
        bmwlist.add(new BmwModel(R.drawable.bmwx1,"BMW X1"," Price : 50.60 Lakhs"));
        bmwlist.add(new BmwModel(R.drawable.bmwx3,"BMW X3"," Price : 73.10 Lakhs"));
        bmwlist.add(new BmwModel(R.drawable.bmwx4,"BMW X4"," Price : 96.20 Lakhs"));
        bmwlist.add(new BmwModel(R.drawable.bmw_xm,"BMW XM"," Price: 2.15 Crore"));
        bmwlist.add(new BmwModel(R.drawable.bmw_m8,"BMW M8"," Price: 2.38 Crore"));
        bmwlist.add(new BmwModel(R.drawable.bmwx6,"BMW X6"," Price: 90 Lakhs"));


        BmwAdapter bmwAdapter = new BmwAdapter(bmwlist,getActivity());
        bmwrecyclerview.setAdapter(bmwAdapter);



        return view;
    }
}