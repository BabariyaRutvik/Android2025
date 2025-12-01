package com.example.creditrack.CredActivity.CredFragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.creditrack.CredActivity.AddFragmentDatabase.Loan;
import com.example.creditrack.CredActivity.AddFragmentDatabase.LoanViewModel;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentLoanDetailsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class LoanDetailsFragment extends Fragment {

    private FragmentLoanDetailsBinding binding;
    LoanViewModel loanViewModel;
    NavController navController;

    int loanId = 0;

    Loan selectedLoan;


    public LoanDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentLoanDetailsBinding.inflate(inflater, container, false);


        // now initializing navcantroller
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);
        loanViewModel = new ViewModelProvider(requireActivity()).get(LoanViewModel.class);


        // receiving loan id from adapter class
        if (getArguments() != null) {
            loanId = getArguments().getInt("loanId", 0);
        }


        LoadLoanDetails();




        return binding.getRoot();
    }
    private void LoadLoanDetails(){

        if (loanId > 0) {
            // Load specific loan if ID is provided
            loanViewModel.GetLoanById(loanId).observe(getViewLifecycleOwner(), new Observer<Loan>() {
                @Override
                public void onChanged(Loan loan) {
                    updateUI(loan);
                }
            });
        } else {
            // Load the last inserted loan if no ID is provided (e.g., from Navigation Drawer)
            loanViewModel.GetLastLoan().observe(getViewLifecycleOwner(), new Observer<Loan>() {
                @Override
                public void onChanged(Loan loan) {
                    updateUI(loan);
                }
            });
        }
    }

    private void updateUI(Loan loan) {
        if (loan != null) {
            selectedLoan = loan;
            // Update loanId in case we fetched the last loan, so Edit/Delete operations work on the correct loan
            loanId = loan.getId();

            binding.textLoanAmount.setText("₹ " + loan.getAmount());
            binding.textLoanType.setText(loan.getLoanType());
            binding.textPersonName.setText(loan.getPersonName());
            binding.textDate.setText(loan.getDate());
            binding.textNote.setText(loan.getNote());

            if ("Given".equalsIgnoreCase(loan.getLoanType())) {
                binding.textLoanType.setBackgroundResource(R.drawable.bg_loan_type);
            } else {
                binding.textLoanType.setBackgroundResource(R.drawable.bg_loan_type_red);
            }

            binding.loanDetailsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowBottom();
                }
            });
        }
    }

    private void ShowBottom(){

        // setting up a bottom shhet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog
                (requireContext());
         View bottomSheetView = LayoutInflater.from(requireContext()).inflate(
                R.layout.bottom_update_delete_add,null);

         bottomSheetDialog.setContentView(bottomSheetView);


         // setting up a add,delete and update the loan
        LinearLayout addLoan = bottomSheetView.findViewById(R.id.add_payment_layout);
        LinearLayout editLoan = bottomSheetView.findViewById(R.id.edit_loan_layout);
        LinearLayout deleteLoan = bottomSheetView.findViewById(R.id.delete_loan_layout);


        // now adding the payment
        addLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                Bundle bundle = new Bundle();
                bundle.putInt("loanId",loanId);
                navController.navigate(R.id.nav_add_payment,bundle);

            }
        });
        // now edit the loan
        editLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                Bundle bundle = new Bundle();
                bundle.putInt("loanId",loanId);
                bundle.putBoolean("isEdit",true);
                navController.navigate(R.id.nav_add_loan,bundle);
            }
        });
        // now Deleting the loan
        deleteLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                ShowDeleteConfirmation();

            }
        });

        bottomSheetDialog.show();

    }
    private void ShowDeleteConfirmation() {

        // Alert Dialog to Delete the loan
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setTitle("Delete Loan")
                .setMessage("Are you sure you want to delete this loan?")
                .setIcon(R.drawable.delete_loan_cred)
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Delete the loan
                    if (selectedLoan != null) {
                        loanViewModel.DeleteLoan(selectedLoan);
                        Toast.makeText(requireContext(), "Loan Deleted", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.nav_home);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                });

        // Create and show the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}