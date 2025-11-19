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

import com.example.autohub.CarAdapter.ToyotaAdapter;
import com.example.autohub.CarModel.ToyotaModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class Toyota_Fragment extends Fragment {

    RecyclerView toyota_recyclerview;
    ArrayList<ToyotaModel> toyotaList;

    ToyotaAdapter toyotaAdapter;


    public Toyota_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_toyota_, container, false);


        toyota_recyclerview = view.findViewById(R.id.toyota_recyclerview);
        toyota_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        toyotaList = new ArrayList<>();

        toyotaList.add(new ToyotaModel(R.drawable.toyota_fortuner,"Toyota Fortuner"," Price : 33,65 - 48.85 Lakhs",
                "The Toyota Fortuner is" +
                        " a powerful and rugged SUV known " +
                        "for its strong road presence, off-road " +
                        "capability, and reliable performance, " +
                        "making it a popular " +
                        "choice among adventure " +
                        "and family SUV buyers.","August 9, 2009"));

        toyotaList.add(new ToyotaModel(R.drawable.toyota_fortuner_legender,"Toyota Fortuner Legender"," Price : 41.54 - 46.75 Lakhs",
                "The Toyota Fortuner Legender " +
                        "is a premium variant of the Fortuner, " +
                        "offering a more stylish design, advanced features, " +
                        "and an upscale interior for a luxurious " +
                        "driving experience."," January , 2021"));
        toyotaList.add(new ToyotaModel(R.drawable.toyota_innova_hycross,"Toyota Innova Hycross"," Price : 18.86 - 31.90 Lakhs",
                "The Toyota Innova HyCross is a " +
                        "premium 7/8-seater MPV with SUV-inspired " +
                        "styling, available in petrol and self-charging " +
                        "hybrid options, offering advanced features " +
                        "like powered ottoman seats, " +
                        "ventilated front seats and Toyota " +
                        "Safety Sense."," December 22 , 2022"));
        toyotaList.add(new ToyotaModel(R.drawable.toyota_taisor,"Toyota Taisor"," Price : 7.09 - 12.06 Lakhs",
                "The Toyota Urban Cruiser " +
                        "Taisor is a compact SUV from " +
                        "Toyota that offers petrol, " +
                        "turbo-petrol and CNG options, " +
                        "packed with modern features " +
                        "like a 9-inch touchscreen, 360° " +
                        "camera and 6 airbags across " +
                        "trims — built to combine Toyota’s " +
                        "reliability with youthful design."," April 03 , 2024"));

        toyotaList.add(new ToyotaModel(R.drawable.toyota_camry,"Toyota Camry"," Price : 47.58 Lakhs",
                "A premium executive sedan " +
                        "that combines Toyota’s hybrid " +
                        "powertrain (2.5-litre petrol + " +
                        "electric motor ~230 hp), refined " +
                        "luxury features and advanced " +
                        "safety tech — ideal for those " +
                        "seeking comfort, efficiency " +
                        "and prestige."," December 11 , 2024"));

        toyotaList.add(new ToyotaModel(R.drawable.toyota_vellfire,"Toyota Vellfire"," Price : 1.20 - 1.30 Crore",
                "The Vellfire is a " +
                        "luxury 6-/7-seater MPV " +
                        "with a strong hybrid " +
                        "powertrain, ultra-premium " +
                        "cabin features (lounge-style " +
                        "seats, large infotainment screen) " +
                        "and sliding " +
                        "doors—designed for executive " +
                        "travel and high-end comfort."," August 03, 2023"));
        toyotaList.add(new ToyotaModel(R.drawable.toyota_land_crusial_300,"Toyota Land Crusial 300"," Price : 2.16 Crore",
                "The Toyota Land " +
                        "Cruiser 300 is a premium " +
                        "full-size luxury SUV built" +
                        " on a body-on-frame chassis, " +
                        "featuring a 3.3-litre twin-turbo " +
                        "V6 diesel engine producing ~309 hp " +
                       ""," January , 2023"));


        toyotaList.add(new ToyotaModel(R.drawable.toyota_glanza,"Toyota Glanza"," Price : 6.89 Lakhs",
                "The Toyota Glanza is a premium " +
                        "hatchback from Toyota (India) that " +
                        "offers a generous feature-set and " +
                        "efficient 1.2-litre petrol engine " +
                        "in a stylish package. It combines the " +
                        "practicality and build of its sibling " +
                        "platform with Toyota’s " +
                        "product-support reputation, making it a " +
                        "smart choice in the 5-seater hatch category."," March 15 , 2022"));


        toyotaAdapter = new ToyotaAdapter(getActivity(),toyotaList);
        toyota_recyclerview.setAdapter(toyotaAdapter);


        toyota_recyclerview.setOnTouchListener((v, event) -> {
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
            if (!toyotaAdapter.isSelectionMode()){
                toyotaAdapter.setSelectionMode(true);
                item.setIcon(R.drawable.select_check);
            }
            else {
                toyotaAdapter.setSelectionMode(false);

                ArrayList<ToyotaModel> selectedtoyota = toyotaAdapter.getSelectedToyota();


                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedcars",selectedtoyota);
                Navigation.findNavController(requireView()).navigate(R.id.nav_details);

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}