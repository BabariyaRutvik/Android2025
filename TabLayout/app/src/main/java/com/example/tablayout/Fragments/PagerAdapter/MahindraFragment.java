package com.example.tablayout.Fragments.PagerAdapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tablayout.FragmentAdapter.MahindraAdapter;
import com.example.tablayout.Model.AudiModel;
import com.example.tablayout.Model.MahindraModel;
import com.example.tablayout.R;

import java.util.ArrayList;


public class MahindraFragment extends Fragment {

    RecyclerView mahindra_recyclerview;
    ArrayList<MahindraModel> mahindraList;


    public MahindraFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mahindra, container, false);


        mahindra_recyclerview = view.findViewById(R.id.mahindra_recyclerview);
        mahindra_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        mahindraList = new ArrayList<>();


        mahindraList.add(new MahindraModel(R.drawable.mahindra_be_6,"Mahindra Be 6e"," Price : 18. 90 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xev_9e,"Mahindra XEV 9e"," Price : 21.90 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_scoriio_n,"Mahindra Scorpio n"," Price : 17. 76 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_700,"Mahindra Xuv 700"," Price : 13. 60 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_200,"Mahindra XUV 200"," Price : 7.18 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_300,"Mahindra XUV 300"," Price : 7.90 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_400_ev,"Mahindra XUV 400 ev"," Price : 15.90 Lakhs"));
        mahindraList.add(new MahindraModel(R.drawable.mahindra_xuv_500,"Mahindra XUV 500"," Price : 14. 30 Lakhs"));


        MahindraAdapter mahindraAdapter = new MahindraAdapter(getContext(),mahindraList);
        mahindra_recyclerview.setAdapter(mahindraAdapter);


        return  view;

    }
}