package com.example.creditrack.CredActivity.CredFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.example.creditrack.CredActivity.PaymentDatabase.Payment;
import com.example.creditrack.CredActivity.PaymentDatabase.PaymentViewModel;
import com.example.creditrack.CredActivity.Reminder.ReminderReceiver;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentAddPaymentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPaymentFragment extends Fragment {

    private FragmentAddPaymentBinding binding;
    PaymentViewModel paymentViewModel;
    LoanViewModel loanViewModel;
    NavController navController;

    private int loanId = -1;
    private String paymentType = "Paid";
    private long selectedDateMillis = 0;

    public AddPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentAddPaymentBinding.inflate(inflater, container, false);

        // Initialize NavController
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);

        // Initialize the PaymentViewModel
        paymentViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        // Initialize the LoanViewModel
        loanViewModel = new ViewModelProvider(this).get(LoanViewModel.class);

        // getArguments()
        if (getArguments() != null) {
            loanId = getArguments().getInt("loanId", -1);
        }

        // now making the click event to the Date
        binding.editAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDate();
            }
        });

        // toggle payment type
        binding.paymentTypeGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
           if (checkedId == R.id.btn_paid && isChecked) {
               paymentType = "Paid";
           } else if (checkedId == R.id.btn_received && isChecked) {
               paymentType = "Received";
           }

        });

        // Save Payment Button
        binding.btnSavePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePayment();
            }
        });

        return binding.getRoot();
    }
    // now making a method for open datepicker

    private void OpenDate(){
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.show(getParentFragmentManager(), "DATE_PICKER");


        datePicker.addOnPositiveButtonClickListener(selection -> {
            selectedDateMillis = selection; // Save the selected timestamp
            Calendar calendar = Calendar.getInstance(Locale.getDefault());

           calendar.setTimeInMillis(selection);


           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
           String formattedDate = simpleDateFormat.format(calendar.getTime());
           binding.editAddDate.setText(formattedDate);

        });
    }
    // now making a method for save payment
    private void SavePayment() {

        String amountStr = binding.editAddAmount.getText().toString();
        String date = binding.editAddDate.getText().toString();
        String note = binding.editAddNote.getText().toString();


        // validating to the fields
        if (amountStr.isEmpty() || date.isEmpty() || note.isEmpty()) {
            Toast.makeText(requireContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        
        if (loanId != -1) {
             Payment payment = new Payment(loanId, amount, date, note, paymentType);
             paymentViewModel.InsertPayment(payment);
             
             // update loan amount in the loan table
             loanViewModel.GetLoanById(loanId).observe(getViewLifecycleOwner(), loan -> {

                if (loan != null){
                    double updatedAmount;
                    if (paymentType.equals("Paid")){
                        updatedAmount = loan.getAmount() - amount; // it reducing the balance

                    }
                    else {
                        updatedAmount = loan.getAmount() + amount; // it increasing the balance
                    }
                    // now updating the loan
                   Loan updateLoan = new Loan(loan.getPersonName(), loan.getLoanType(),
                           updatedAmount,loan.getDate(), loan.getNote());
                    updateLoan.setId(loanId);
                    loanViewModel.UpdateLoan(updateLoan);
                    
                    // Schedule Notification
                    if (selectedDateMillis > System.currentTimeMillis()) {
                        scheduleNotification(selectedDateMillis, amountStr, note, loan.getPersonName());
                    }

                }
            });
             
             Toast.makeText(requireContext(), "Payment Added Successfully", Toast.LENGTH_SHORT).show();
             navController.navigateUp();
        } else {
             Toast.makeText(requireContext(), "Error: Loan ID not found", Toast.LENGTH_SHORT).show();
        }

    }

    private void scheduleNotification(long timeInMillis, String amount, String note, String personName) {
        Intent intent = new Intent(requireContext(), ReminderReceiver.class);
        intent.putExtra("amount", amount);
        intent.putExtra("note", note);
        intent.putExtra("personName", personName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                (int) System.currentTimeMillis(), // Unique ID for each alarm
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            // Use setExactAndAllowWhileIdle for exact timing even in low power modes
            // Or setRepeating if you want repeats (not requested here)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
            Toast.makeText(requireContext(), "Reminder Set", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}