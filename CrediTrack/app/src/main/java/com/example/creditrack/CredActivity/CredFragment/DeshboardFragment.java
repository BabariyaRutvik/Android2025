package com.example.creditrack.CredActivity.CredFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.creditrack.CredActivity.AddFragmentDatabase.Loan;
import com.example.creditrack.CredActivity.AddFragmentDatabase.LoanViewModel;
import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.CredActivity.Database.LoginHelper;
import com.example.creditrack.CredActivity.LoanAdapter.DashboardAdapter;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentDeshboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DeshboardFragment extends Fragment {

    private FragmentDeshboardBinding binding;
    DashboardAdapter dashboardAdapter;
    LoanViewModel loanViewModel;
    NavController navController;
    
    // For fetching user name
    LoginHelper loginHelper;
    LoanManager loanManager;
    
    // Store all loans to filter for search
    private List<Loan> allLoansList = new ArrayList<>();


    public DeshboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentDeshboardBinding.inflate(inflater, container, false);


        // initializing Navcantroller
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);
        
        // Initialize Helpers
        loginHelper = new LoginHelper(requireContext());
        loanManager = new LoanManager(requireContext());


        // setting up a recyclerview to the Dashboard

        binding.recyclerLoanDash.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerLoanDash.setHasFixedSize(true);
        // Fix scrolling issue inside ScrollView
        binding.recyclerLoanDash.setNestedScrollingEnabled(false);


        // Adapter initialization
        dashboardAdapter = new DashboardAdapter(requireContext(), new ArrayList<>(), loan -> {
                Bundle bundle = new Bundle();
                bundle.putInt("loanId", loan.getId());
                navController.navigate(R.id.nav_loan_details, bundle);


        });
        binding.recyclerLoanDash.setAdapter(dashboardAdapter);

        // Search functionality
        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterLoans(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
        
        // Set User Name
        SetUserName();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initializing the loan viewmodel
        loanViewModel = new ViewModelProvider(this).get(LoanViewModel.class);


        // getting all loans
        loanViewModel.GetAllLoans().observe(getViewLifecycleOwner(), loans -> {
            // if loans is not null then print 10 loans
            if (loans != null) {
                allLoansList = loans; // Store the full list
                
                // showing only 10 loans initially or when search is empty
                List<Loan> recentLoans;
                if (loans.size() > 10){
                    recentLoans = loans.subList(loans.size()-10,loans.size());

                }
                else {
                    recentLoans = loans;
                }
                dashboardAdapter.setLoansList(recentLoans);
                binding.textDashTotalPerson.setText(String.valueOf(loans.size()));


                }

        });

        // total Given Loans
        loanViewModel.GetTotalGiven().observe(getViewLifecycleOwner(), value -> {
           double amount = value != null ? value : 0.0;
           binding.textDashGiven.setText("₹ " + String.format("%.2f", amount));
           updateBalance();

        });

        // total taken
        loanViewModel.GetTotalTaken().observe(getViewLifecycleOwner(), value -> {
            double amount = value != null ? value : 0.0;
            binding.textDashTaken.setText("₹ " + String.format("%.2f", amount));
            updateBalance();

        });





    }
    
    private void SetUserName() {
        int userId = loanManager.getUser();
        if (userId != -1 && userId != 0) {
            String name = loginHelper.getUserName(userId);
            binding.textGreetings.setText("Hello, " + name + " \uD83D\uDC4B");
        }
    }
    
    private void updateBalance() {
        String givenStr = binding.textDashGiven.getText().toString().replace("₹ ", "").trim();
        String takenStr = binding.textDashTaken.getText().toString().replace("₹ ", "").trim();
        
        double given = 0.0;
        double taken = 0.0;
        
        try {
            if (!givenStr.isEmpty()) given = Double.parseDouble(givenStr);
            if (!takenStr.isEmpty()) taken = Double.parseDouble(takenStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        double balance = given - taken;
        binding.textDashBalance.setText("₹ " + String.format("%.2f", balance));
    }
    
    private void filterLoans(String query) {
        List<Loan> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            // If search is empty, show the recent 10 loans (original behavior)
             if (allLoansList.size() > 10){
                filteredList = allLoansList.subList(allLoansList.size()-10,allLoansList.size());
            } else {
                filteredList = allLoansList;
            }
        } else {
            // Filter based on name
            for (Loan loan : allLoansList) {
                if (loan.getPersonName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(loan);
                }
            }
        }
        dashboardAdapter.setLoansList(filteredList);
    }
    
    private String formatAmount(double amount){
        return String.format("%.2f", amount);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}