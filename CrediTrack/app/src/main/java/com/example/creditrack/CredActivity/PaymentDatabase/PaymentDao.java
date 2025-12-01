package com.example.creditrack.CredActivity.PaymentDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaymentDao {

    // inserting the payment
    @Insert
    void insertPayment(Payment payment);


    // getting all payments by loanid
    @Query("SELECT *FROM payments WHERE loanId =:loanId ORDER BY id DESC")
    LiveData<List<Payment>> getAllPaymentsByLoanId(int loanId);



    // now getting a Total Paid Amount
    @Query("SELECT SUM(amount) FROM payments WHERE loanId =:loanId AND payment_type = 'Paid'")
    LiveData<Double> getTotalPaidAmount(int loanId);


    // now getting a Total Received Amount

    @Query(("SELECT SUM(amount) FROM payments WHERE loanId =:loanId AND payment_type = 'Received'"))
    LiveData<Double> getTotalReceivedAmount(int loanId);



}
