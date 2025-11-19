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

import com.example.autohub.CarAdapter.MahindraAdapter;
import com.example.autohub.CarModel.MahindraModel;
import com.example.autohub.R;

import java.util.ArrayList;


public class MahindraFragment extends Fragment {


    RecyclerView mahindra_recyclerview;
    ArrayList<MahindraModel> mahindraList;

    MahindraAdapter mahindraAdapter;

    public MahindraFragment() {
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
        View view = inflater.inflate(R.layout.fragment_mahindra, container, false);

        mahindra_recyclerview = view.findViewById(R.id.mahindra_recyclerview);
        mahindra_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        mahindraList = new ArrayList<>();

        mahindraList.add(new MahindraModel(R.drawable.mahindra_be_6,"Mahindra be 6e","Price : 18.90 Lakhs",
                "Here’s a summary of the" +
                        " Mahindra BE 6e (sometimes referenced in early materials as “B6 6e” " +
                        "but correctly the BE 6e) electric SUV from Mahindra, focusing " +
                        "on the specs, features and key considerations " +
                        "for the Indian market.","November 26 , 2024"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xev_9e,"Mahindra XEV 9E","Price : 20.90 - 31.25 Lakhs",
                "The XEV 9e offers a very competitive " +
                        "combination of range, performance and premium features " +
                        "in the Indian EV market.\n" +
                        "\n" +
                        "Rear-wheel " +
                        "drive, large battery option, " +
                        "and strong claimed range make it appealing for those" +
                        " wanting a “serious” electric SUV rather than just commuter EV.","November 26 , 2024"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_700,"Mahindra XUV 700","Price : 13.60 Lakhs",
                "Strong value proposition: For what you get (7-seater, premium features, " +
                        "strong engines) it is very " +
                        "competitive in the Indian SUV segment."," April 14 , 2021"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_300,"Mahindra XUV 300","Price : 13.60 Lakhs",
                "The Mahindra XUV300 is a " +
                        "compact 5-seater SUV known for its " +
                        "strong performance, premium features, " +
                        "and excellent safety rating. It offers " +
                        "both petrol and diesel engines with impressive " +
                        "torque, making it ideal for city and highway " +
                        "driving. With a stylish design, modern tech " +
                        "like dual-zone climate control and a " +
                        "sunroof, it combines comfort, safety, and " +
                        "practicality in a compact form."," February 19, 2019"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_200,"Mahindra XUV 200","Price : 14.40 Lakhs",
                "The Mahindra XUV200 is an upcoming compact SUV from Mahindra, expected to feature bold styling " +
                        "and competitive pricing in " +
                        "the ₹8.5-12 lakh range in India."," November 26, 2018"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_scoriio_n,"Mahindra Scorpio n","Price : 24.67 Lakhs",
                "The Mahindra Scorpio-N is a powerful and " +
                        "rugged SUV offering strong performance, modern " +
                        "features, and off-road " +
                        "capability, making it ideal for both city and adventure " +
                        "drives."," June 27, 2024"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_500,"Mahindra Xuv 500","Price : 14.23 Lakhs",
                "The Mahindra XUV500 is " +
                        "a mid-sized, 7-seater SUV known " +
                        "for its strong diesel engine " +
                        "(2.2 L turbo-diesel producing ~155 PS and " +
                        "~360 Nm of torque in later models) " +
                        "and SUV-style presence in India."," November , 2021"));

        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_400_ev,"Mahindra Xuv 400 ev","Price : 14.23 Lakhs",
                "The Mahindra XUV400 EV is a " +
                        "compact all-electric SUV offering " +
                        "up to ~456 km of MIDC-cycle range with a 39.4 " +
                        "kWh battery, 0-100 km/h in about" +
                        " 8.3 seconds, and instant torque of 310 Nm. \n" +
                        "Mahindra\n" +
                        "+2\n" +
                        "Mahindra Auto\n" +
                        "+2\n" +
                        " It features a five-seater layout, " +
                        "generous cabin width (1,821 mm)," +
                        " boot space ~378 litres, six-airbag safety, " +
                        "IP67 rated battery pack, and compatibility " +
                        "with DC fast charging (80% in ~50 minutes)."," January , 2023"));


        mahindraAdapter = new MahindraAdapter(getActivity(),mahindraList);
        mahindra_recyclerview.setAdapter(mahindraAdapter);

        mahindra_recyclerview.setOnTouchListener((v, event) -> {
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
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem selectItem = menu.findItem(R.id.select_item);
        if (mahindraAdapter != null && mahindraAdapter.isSelectionMode()) {
            selectItem.setIcon(R.drawable.select_check);
            selectItem.setTitle("Cancel");
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.select_item){
            if (!mahindraAdapter.isSelectionMode()){
                mahindraAdapter.setSelectionMode(true);

            }
            else {
                mahindraAdapter.setSelectionMode(false);
                ArrayList<MahindraModel>selectedMahindra = mahindraAdapter.getSelectedMahindra();


                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedcars",selectedMahindra);
                Navigation.findNavController(requireView()).navigate(R.id.nav_details, bundle);

            }
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}