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
import com.example.creditrack.CredActivity.LoanAdapter.PersonalListAdapter;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentPersonalListBinding;

import java.util.ArrayList;
import java.util.List;

public class PersonalListFragment extends Fragment {

    private FragmentPersonalListBinding binding;
    PersonalListAdapter listAdapter;
    NavController navController;
    LoanViewModel loanViewModel;
    
    // List to store all loans for searching
    private List<Loan> allLoansList = new ArrayList<>();


    public PersonalListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentPersonalListBinding.inflate(inflater, container, false);

        binding.recyclerPersonal.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerPersonal.setHasFixedSize(true);


        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);

        // now initializing adapter class
        listAdapter = new PersonalListAdapter(getContext(), new ArrayList<>(), loan -> {
           Bundle bundle = new Bundle();
           bundle.putInt("loanId",loan.getId());
           navController.navigate(R.id.nav_loan_details,bundle);

        });
        binding.recyclerPersonal.setAdapter(listAdapter);

        // Setup Search functionality
        binding.editSearchPersonal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel initializing
        loanViewModel = new ViewModelProvider(requireActivity()).get(LoanViewModel.class);

        // now observing all database list
        loanViewModel.GetAllLoans().observe(getViewLifecycleOwner(), list -> {
            if (list != null){
                allLoansList = list; // Store original list
                listAdapter.setLoanList(list); // Display list
            }

        });
    }

    private void filterList(String text) {
        List<Loan> filteredList = new ArrayList<>();
        for (Loan loan : allLoansList) {
            if (loan.getPersonName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(loan);
            }
        }
        listAdapter.setLoanList(filteredList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}