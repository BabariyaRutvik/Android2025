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

import com.example.autohub.CarAdapter.AudiAdapter;
import com.example.autohub.CarModel.AudiModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class Audi_Fragment extends Fragment {

    RecyclerView audi_recyclerview;
    ArrayList<AudiModel> audiList;
    AudiAdapter audiAdapter;

    public Audi_Fragment() {
        // Required empty public constructor
    }

    // enabling menu functionality for fragment
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
        View view = inflater.inflate(R.layout.fragment_audi_, container, false);


        audi_recyclerview = view.findViewById(R.id.audi_recyclerview);
        audi_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        audiList = new ArrayList<>();


        audiList.add(new AudiModel(R.drawable.audi_q3, "Audi Q3", "43.07 Lakhs",
                "The Audi Q3 is a premium compact crossover " +
                        "SUV that blends Audi’s " +
                        "luxury brand character " +
                        "with practical versatility.", "June , 2025"));
        audiList.add(new AudiModel(R.drawable.audi_a4, "Audi A4", "46.25 Lakhs",
                "The Audi A4 is a luxury compact " +
                        "executive sedan (and also available as " +
                        "an Avant/wagon in many markets) from the " +
                        "German manufacturer Audi. It combines elegance, " +
                        "modern technology and everyday " +
                        "usability in a premium-brand " +
                        "package.", "June 9 , 2025"));
        audiList.add(new AudiModel(R.drawable.audi_q5, "Audi Q5", "64.74 Lakhs",
                "The Audi Q5 is a premium " +
                        "mid-sized luxury SUV from Audi that strikes a " +
                        "good balance between everyday usability, comfort," +
                        " and classy dynamics.", " November , 2021"));

        audiList.add(new AudiModel(R.drawable.audi_q6, "Audi Q6", " 85.00 Lakhs",
                "The Audi Q5 is a premium " +
                        "A full-size three-row SUV made for the " +
                        "Chinese market, produced by SAIC‑Volkswagen" +
                        " (a joint venture) under the Audi brand. " +
                        "It’s large in dimensions (about 5.1 m length) " +
                        "and offered with petrol engines.", " December , 2025"));

        audiList.add(new AudiModel(R.drawable.audi_q7, "Audi Q7", " 88.14 Lakhs",
                "The Audi Q5 is a premium " +
                        "The Audi Q7 is a luxury " +
                        "mid-/large-size SUV offering three " +
                        "rows of seating (up to 7 occupants) " +
                        "and aiming to combine " +
                        "premium comfort, space and " +
                        "technology with Audi’s refined driving character.", " November 18 , 2024"));

        audiList.add(new AudiModel(R.drawable.audi_q8, "Audi Q8", " 1.10 Crore",
                "The Audi Q8 is a full-size luxury SUV " +
                        "that blends imposing SUV proportions with a " +
                        "more coupe-like roofline and sporty aesthetic. " +
                        "It sits near the top of Audi’s SUV line-up in many " +
                        "markets and offers premium finishes, " +
                        "advanced tech and strong performance.", " August 22, 2024"));

        audiList.add(new AudiModel(R.drawable.audi_rs7, "Audi Rs7", " 2.20 Crore",
                "The Audi RS7 is a high-performance " +
                        "“Sportback” model from Audi, combining the luxury and technology of a premium sedan with the brute power of a sports car. It uses a coupé-style four-door layout " +
                        "(with a sporty liftback rear) so you" +
                        " get practicality plus thrilling performance.", " July 16 , 2020"));

        audiList.add(new AudiModel(R.drawable.audi_s5_sportback, "Audi s5 Sportsback", "73. 57 Lakhs ",
                "The Audi S5 Sportback is a " +
                        "performance-oriented version " +
                        "of Audi’s A5 family, " +
                        "combining the sleek " +
                        "fast-back 5-door “Sportback” " +
                        "body style with a potent " +
                        "engine and the brand’s " +
                        "quattro all-wheel-drive system. " +
                        "It blends sporty performance " +
                        "with practical " +
                        "usability (4 doors + hatch-style rear) — " +
                        "making it a stylish, yet usable premium car. \n" +
                        "CarWale\n" +
                        "+3", " March 22 , 2021"));


        audiAdapter = new AudiAdapter(getActivity(), audiList);
        audi_recyclerview.setAdapter(audiAdapter);

        audi_recyclerview.setOnTouchListener((v, event) -> {
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
        inflater.inflate(R.menu.select_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem selectItem = menu.findItem(R.id.select_item);
        if (audiAdapter != null && audiAdapter.isSelectionMode()) {
            selectItem.setIcon(R.drawable.select_check);
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.select_item) {
            if (!audiAdapter.isSelectionMode()) {
                audiAdapter.setSelectionMode(true);

            } else {
                audiAdapter.setSelectionMode(false);
                ArrayList<AudiModel> selectedAudi = audiAdapter.getSelectedAudi();


                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedcars", selectedAudi);
                Navigation.findNavController(requireView()).navigate(R.id.nav_details, bundle);


            }
            return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
