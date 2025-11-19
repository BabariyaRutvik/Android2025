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

import com.example.autohub.CarAdapter.HyundaiAdapter;
import com.example.autohub.CarModel.HyundaiModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class Hyundai_Fragment extends Fragment {

   RecyclerView hyundai_recyclerview;
   ArrayList<HyundaiModel> hyundaiList;

   HyundaiAdapter hyundaiAdapter;

    public Hyundai_Fragment() {
        // Required empty public constructor
    }
    // enabling fragment menu functionality


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // enable menu for this fragment
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_hyundai_, container, false);


        hyundai_recyclerview = view.findViewById(R.id.hyundai_recyclerview);
        hyundai_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        hyundaiList = new ArrayList<>();


        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_creta,"Hyundai Creta"," Price : 20.50 Lakhs",
                "It’s a stylish and " +
                        "feature-rich compact SUV " +
                        "offering a wide range of engine " +
                        "and variant options, and strikes " +
                        "a strong balance between urban " +
                        "usability and highway comfort.\n" +
                        "\n" +
                        "\n","January 17, 2025"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_verna,"Hyundai Verna"," Price : 17.38 Lakhs",
                "A stylish, feature-rich " +
                        "midsize sedan offering robust " +
                        "engine options, modern connectivity " +
                        "and driver-assist tech — designed " +
                        "to elevate the segment’s " +
                        "value and appeal."," March 21, 2023"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_exter,"Hyundai Exter"," Price : 9.99  Lakhs",
                "The Exter is a stylish " +
                        "entry-level micro-SUV from " +
                        "Hyundai that offers SUV-looks, " +
                        "a sunroof, strong connected-car features " +
                        "and multiple " +
                        "fuel-options (petrol and CNG) " +
                        "at an attractive price point."," July 10, 2023"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_alcazar,"Hyundai Alcazar"," Price : 14.99  Lakhs",
                "The Hyundai Alcazar " +
                        "is a premium 6/7-seater " +
                        "SUV based on the Creta, offering " +
                        "a spacious cabin, strong feature list " +
                        "and choice of" +
                        " petrol and diesel engines for families " +
                        "seeking both comfort and versatility."," June 18, 2021"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_aura,"Hyundai Aura"," Price : 5.80 Lakhs",
                "he Hyundai Aura is a " +
                        "stylish compact sedan offering " +
                        "modern features like wireless phone charging, a large infotainment touchscreen and various engine options—including petrol, " +
                        "turbo-petrol and diesel—making " +
                        "it a smart choice for urban " +
                        "families."," Junuary 21, 2020"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_verna,"Hyundai Verna"," Price : 10.90 - 17.38 Lakhs",
                "The Hyundai Verna is a " +
                        "stylish and feature-rich midsize" +
                        " sedan offering modern connectivity, " +
                        "turbo-petrol and naturally aspirated " +
                        "engines, and advanced driver-assistance " +
                        "systems, making it a strong contender " +
                        "in the premium sedan segment."," March 21, 2023"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_creta_electric,"Hyundai Creta Electric"," Price : 17.99 - 23. 49 Lakhs",
                "This electric version of " +
                        "the popular Creta SUV offers two " +
                        "battery options " +
                        "(42 kWh with ~390 km range and 51.4 kWh with ~473 km range) " +
                        "and packs modern tech like ADAS Level 2, dual screens and " +
                        "fast-charging support—making it a strong contender " +
                        "in the EV SUV segment."," January 17, 2025"));

        hyundaiList.add(new HyundaiModel(R.drawable.hyundai_i20,"Hyundai i20"," Price : 6.99 - 11.01 Lakhs",
                "The Hyundai i20 is a premium " +
                        "hatchback that blends stylish design, " +
                        "modern technology and a wide range of " +
                        "power-train options " +
                        "(petrol, diesel) to deliver an upscale " +
                        "urban driving experience."," November 05, 2020"));


        hyundaiAdapter = new HyundaiAdapter(getActivity(),hyundaiList);
        hyundai_recyclerview.setAdapter(hyundaiAdapter);


        hyundai_recyclerview.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN ||
                    event.getAction() == android.view.MotionEvent.ACTION_MOVE) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            }
            return false;
        });






        return  view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.select_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.select_item){
            if (!hyundaiAdapter.isSelectionMode()){
                hyundaiAdapter.setSelectionMode(true);
                item.setIcon(R.drawable.select_check);

            }
            else {
                hyundaiAdapter.setSelectionMode(false);
                ArrayList<HyundaiModel> selectedHyundai = hyundaiAdapter.getSelectedHyundai();


                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedcars",selectedHyundai);
                Navigation.findNavController(requireView()).navigate(R.id.nav_details);

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}