package com.example.creditrack.CredActivity.AddFragmentDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoanDao {

    // inserting the loan
    @Insert
    void insert(Loan loan);

    // for Delete the loan
    @Delete
    void delete(Loan loan);

    // for update the loan
    @Update
    void update(Loan loan);


    // For getting all the loan
    @Query("SELECT * FROM loan_table")
    LiveData<List<Loan>> getAllLoans();


    // for Total loan given by the user
    @Query("SELECT SUM(amount) FROM loan_table WHERE loan_type = 'Given'")
    LiveData<Double> getTotalGiven();

    // for Total loan taken by the user

    @Query("SELECT SUM(amount) FROM loan_table WHERE loan_type = 'Taken'")
    LiveData<Double> getTotalTaken();


    // Getting loan by id
    @Query("SELECT * FROM loan_table WHERE id = :loanId")
    LiveData<Loan> getLoanById(int loanId);

    // Get the last inserted loan
    @Query("SELECT * FROM loan_table ORDER BY id DESC LIMIT 1")
    LiveData<Loan> getLastLoan();

}
