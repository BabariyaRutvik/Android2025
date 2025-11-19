package com.example.tripease.TripActivity.TripFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripease.R;
import com.example.tripease.TripActivity.TripAdapter.Top10Adapter;
import com.example.tripease.TripActivity.TripModel.FevoriteManager;
import com.example.tripease.TripActivity.TripModel.Top10Model;

import java.util.ArrayList;


public class BookmarkFragment extends Fragment {

    RecyclerView FevoritesRecyclerView;
    ArrayList<Top10Model> fevoritesList;
    Top10Adapter fevoritesAdapter;




    public BookmarkFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);


        fevoritesList = new ArrayList<>();

        fevoritesList = FevoriteManager.getFavorites(getContext());

        FevoritesRecyclerView = view.findViewById(R.id.fevorite_recyclerView);
        FevoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        fevoritesAdapter = new Top10Adapter(getContext(),fevoritesList);
        FevoritesRecyclerView.setAdapter(fevoritesAdapter);

        return view;
    }
}