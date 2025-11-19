package com.example.autohub.CarFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.autohub.CarAdapter.DetailsCarAdapter;
import com.example.autohub.CarModel.BMWModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class Details_Fragment extends Fragment {


    RecyclerView details_car_recyclerview;
    ArrayList<BMWModel> car_detailsList;

    DetailsCarAdapter detailsCarAdapter;

    public Details_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_, container, false);

        details_car_recyclerview = view.findViewById(R.id.details_car_recyclerview);

        car_detailsList = new ArrayList<>();

        if (getArguments() != null){
            car_detailsList = (ArrayList<BMWModel>) getArguments().getSerializable("selectedcars");
            detailsCarAdapter = new DetailsCarAdapter(getContext(),car_detailsList);
            details_car_recyclerview.setAdapter(detailsCarAdapter);

        }

        return view;
    }
}