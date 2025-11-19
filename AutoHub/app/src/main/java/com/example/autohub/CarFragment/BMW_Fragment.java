package com.example.autohub.CarFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.autohub.CarAdapter.BMWAdapter;
import com.example.autohub.CarModel.BMWModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class BMW_Fragment extends Fragment {

    RecyclerView bmw_recyclerview;
    public ArrayList<BMWModel> bmwList;
    BMWAdapter bmwAdapter;

    public BMW_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_w_, container, false);

        bmw_recyclerview =view.findViewById(R.id.bmw_recyclerview);
        bmw_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        bmwList = new ArrayList<>();

        // Data population (kept as is)
        bmwList.add(new BMWModel(R.drawable.bmwx1,"BMW X1"," Price : 50.60 - 52.20 Lakhs",
                "The BMW X1 is a compact luxury crossover " +
                        "SUV offering the signature " +
                        "“BMW driving experience” " +
                        "in a smaller, more versatile package."," January 23 , 2023"));

        bmwList.add(new BMWModel(R.drawable.bmwx2,"BMW X2"," Price : 45.00 Lakhs",
                "The BMW X2 is a compact luxury " +
                        "crossover with a coupe-SUV silhouette " +
                        "that emphasizes sporty styling " +
                        "over maximal practicality."," December , 2025"));
        bmwList.add(new BMWModel(R.drawable.bmwx3,"BMW X3"," Price : 71.20 - 73.10 Lakhs",
                "The BMW X3 is a mid-size luxury SUV that " +
                        "blends performance, comfort, and practicality. " +
                        "It sits between the smaller " +
                        "X1/X2 and the larger X5 " +
                        "in BMW’s lineup. Known " +
                        "for its balanced driving " +
                        "dynamics, the X3 offers a " +
                        "smooth yet sporty ride with " +
                        "powerful petrol, diesel, and " +
                        "hybrid engine options."," January 19 , 2025"));


        bmwList.add(new BMWModel(R.drawable.bmwx4,"BMW X4"," Price : 96.20 Lakhs",
                "It’s based on the same platform " +
                        "as the X3, yet distinguishes itself with " +
                        "a lower roofline and a " +
                        "fast-back “coupé” silhouette, giving " +
                        "it a more dynamic and athletic " +
                        "appearance versus a traditional SUV."," October 23 , 2023"));

        bmwList.add(new BMWModel(R.drawable.bmwx6,"BMW X4"," Price : 90.00 Lakhs",
                "Premium interior & tech: High-quality " +
                        "materials, large infotainment " +
                        "displays, advanced driver assistance systems, and features " +
                        "like adaptive suspension and all-wheel drive" +
                        " as standard in many markets."," January 11 , 2020"));

        bmwList.add(new BMWModel(R.drawable.bmw_x7,"BMW X7"," Price : 1.20 Crore",
                "It offers turbocharged 6-cylinder " +
                        "and V8 petrol engines (with mild-hybrid support) " +
                        "as well as diesel options in some markets. For example, " +
                        "the xDrive40i uses a 3.0 L I6 with ~381 hp and 540 Nm " +
                        "torque."," January 13 , 2023"));

        bmwList.add(new BMWModel(R.drawable.bmw_xm,"BMW XM"," Price : 2.55 Crore",
                "It uses a plug-in hybrid configuration, " +
                        "pairing a high-performance 4.4-litre twin-turbo " +
                        "V8 petrol engine with an electric motor. Together " +
                        "they deliver up to ~650 hp / " +
                        "800 Nm (in standard form) " +
                        "and even up to ~748 hp / 1,000 Nm in the " +
                        "“Label” version."," December 22 , 2022"));

        bmwList.add(new BMWModel(R.drawable.bmw_m8,"BMW M8"," Price : 2.38 Crore",
                "Performance: Very rapid " +
                        "acceleration — for example 0-60 " +
                        "mph in around 3.0 seconds in the Gran " +
                        "Coupe Competition version. " +
                        "Top speed is electronically " +
                        "limited (with " +
                        "optional higher limit packages) " +
                        "to around 155 mph " +
                        "(~250 km/h) in standard form."," May 08 , 2020"));


        bmwAdapter = new BMWAdapter(getActivity(),bmwList);
        bmw_recyclerview.setAdapter(bmwAdapter);

        // Keep the touch listener as is if it's for scroll conflict resolution
        bmw_recyclerview.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN ||
                    event.getAction() == android.view.MotionEvent.ACTION_MOVE) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            }
            return false;
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.select_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem selectItem = menu.findItem(R.id.select_item);
        if (bmwAdapter != null && bmwAdapter.isSelectionMod()) {
            selectItem.setIcon(R.drawable.select_check);
            selectItem.setTitle("Done");

        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.select_item){
            if (!bmwAdapter.isSelectionMod()){
                // Enter selection mode
                bmwAdapter.setSelectionMode(true);
            }
            else {
                // Exit selection mode (Done button clicked)
                ArrayList<BMWModel>selectedBMW = bmwAdapter.getSelectBMW();

                // Clear selection mode and list (this is handled inside setSelectionMode(false) in the adapter)
                bmwAdapter.setSelectionMode(false);

                // Navigate to the next fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedcars",selectedBMW);
                Navigation.findNavController(requireView()).navigate(R.id.nav_details,bundle);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}