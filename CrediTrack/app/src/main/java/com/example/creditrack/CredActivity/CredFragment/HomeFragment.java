package com.example.creditrack.CredActivity.CredFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.creditrack.CredActivity.AddFragmentDatabase.LoanViewModel;
import com.example.creditrack.CredActivity.LoanAdapter.LoanAdapter;
import com.example.creditrack.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView loanRecycler;
    TextView textTotalGiven;
    TextView textTotalTaken;
    TextView textTotalBalance;


    LoanAdapter loanAdapter;
    NavController navController;
    LoanViewModel loanViewModel;

    ExtendedFloatingActionButton addLoanButton;
    
    private double totalGivenAmount = 0.0;
    private double totalTakenAmount = 0.0;


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loanRecycler = view.findViewById(R.id.loanRecycler);
        textTotalGiven = view.findViewById(R.id.text_total_given);
        textTotalTaken = view.findViewById(R.id.text_total_taken);
        textTotalBalance = view.findViewById(R.id.text_total_balance);
        addLoanButton = view.findViewById(R.id.add_loan_btn);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);


        // setting up recycleview
        loanRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        loanRecycler.setHasFixedSize(true);



        // Initialize Adapter with an empty list initially
        loanAdapter = new LoanAdapter(getContext(), new ArrayList<>(), loan -> {
            // Passing the loan ID to the LoanDetailsFragment
            Bundle bundle = new Bundle();
            bundle.putInt("loanId", loan.getId());
            navController.navigate(R.id.nav_loan_details, bundle);
        });
        loanRecycler.setAdapter(loanAdapter);


        // click event for Add Loan
        addLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_add_loan);
            }
        });

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        loanViewModel = new ViewModelProvider(this).get(LoanViewModel.class);


        // getting all loans from the given to the Employee
        loanViewModel.GetAllLoans().observe(getViewLifecycleOwner(), loans -> {
            if (loans !=null){
                loanAdapter.setLoansList(loans);

            }
        });
        // getting total given loan
        loanViewModel.GetTotalGiven().observe(getViewLifecycleOwner() , value ->{
            totalGivenAmount = value != null ? value : 0.0;
            textTotalGiven.setText("₹ " + formatAmount(totalGivenAmount));
            updateBalance();

        });
        // getting total taken loan
        loanViewModel.GetTotalTaken().observe(getViewLifecycleOwner() , value ->{
            totalTakenAmount = value != null ? value : 0.0;
            textTotalTaken.setText("₹ " + formatAmount(totalTakenAmount));
            updateBalance();
        });
    }

    private void updateBalance() {
        double balance = totalGivenAmount - totalTakenAmount;
        textTotalBalance.setText("₹ " + formatAmount(balance));
    }

    private String formatAmount(double amount){
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        return formatter.format(amount);
    }
}