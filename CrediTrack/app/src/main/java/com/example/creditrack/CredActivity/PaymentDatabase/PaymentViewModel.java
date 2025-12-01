package com.example.creditrack.CredActivity.PaymentDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PaymentViewModel extends AndroidViewModel {

    private PaymentRepository paymentRepository;

    // creating a constructor
    public PaymentViewModel(@NonNull Application application) {
        super(application);
        paymentRepository = new PaymentRepository(application);

    }
    // now inserting the payment
    public void InsertPayment(Payment payment){
        paymentRepository.insertPayment(payment);
    }
    // getting all payments by loanid
    public LiveData<List<Payment>> getAllPaymentsByLoanId(int loanId){
        return paymentRepository.getAllPaymentsByLoanId(loanId);
    }
}
