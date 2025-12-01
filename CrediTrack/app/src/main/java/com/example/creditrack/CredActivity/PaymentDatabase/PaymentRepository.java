package com.example.creditrack.CredActivity.PaymentDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.creditrack.CredActivity.AddFragmentDatabase.LoanDatabase;

import java.util.List;

public class PaymentRepository
{
    private PaymentDao paymentDao;


    public PaymentRepository(Application application){
      LoanDatabase database = LoanDatabase.getInstance(application);
        paymentDao = database.paymentDao();


    }
    // now inserting the payment
    public void insertPayment(Payment payment){
        LoanDatabase.databaseWriteExecutor.execute(() -> paymentDao.insertPayment(payment));

    }
    // getting all payments by loanid

    public LiveData<List<Payment>> getAllPaymentsByLoanId(int loanId) {
        return paymentDao.getAllPaymentsByLoanId(loanId);

    }

}
