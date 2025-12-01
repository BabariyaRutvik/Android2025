package com.example.creditrack.CredActivity.CredFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.creditrack.CredActivity.AddFragmentDatabase.Loan;
import com.example.creditrack.CredActivity.AddFragmentDatabase.LoanViewModel;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentAddLoanBinding;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddLoanFragment extends Fragment {

    private FragmentAddLoanBinding binding;

    LoanViewModel loanViewModel;
    NavController navController;

    String loanType = "Given";

    public AddLoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddLoanBinding.inflate(inflater, container, false);

        // initializing view model
        loanViewModel = new ViewModelProvider(this).get(LoanViewModel.class);

        // initializing nav controller
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);


        // for datePicker
        binding.edtPersonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateShow();
            }
        });

        binding.toggleLoanType.addOnButtonCheckedListener((group, checkedId, isChecked) -> {

            if (checkedId == R.id.btn_given && isChecked){
                loanType = "Given";
            }
            else if (checkedId == R.id.btn_taken && isChecked){
                 loanType ="Taken";
            }
        });


        binding.btnSaveLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveLoan();
            }
        });

        return binding.getRoot();
    }
    private void DateShow() {

        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.show(getParentFragmentManager(), "date_picker");


        datePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            binding.edtPersonDate.setText(dateFormat.format(calendar.getTime()));


        });

    }
    private void SaveLoan() {

          String personName = binding.edtPersonName.getText().toString();
          String personAmount = binding.edtPersonAmount.getText().toString();
          String personDate = binding.edtPersonDate.getText().toString();
          String personNote = binding.edtPersonNote.getText().toString();

          if (personName.isEmpty() || personAmount.isEmpty() || personDate.isEmpty()) {
              Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
          }
          else {
              double amount = Double.parseDouble(personAmount);

              Loan loan = new Loan(personName, loanType, amount, personDate, personNote);
              loanViewModel.InsertLoan(loan);
              Toast.makeText(getContext(), "Loan Added Successfully", Toast.LENGTH_SHORT).show();
              navController.navigate(R.id.nav_home);
          }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}