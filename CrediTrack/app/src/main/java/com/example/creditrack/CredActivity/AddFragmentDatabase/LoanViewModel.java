package com.example.creditrack.CredActivity.AddFragmentDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoanViewModel extends AndroidViewModel {


    private LoanRepository loanRepository;
    private LiveData<List<Loan>> allLoans;
    private LiveData<Double> totalGiven;
    private LiveData<Double> totalTaken;


    public LoanViewModel(@NonNull Application application) {
        super(application);
        loanRepository = new LoanRepository(application);
        allLoans = loanRepository.getAllLoans();
        totalGiven = loanRepository.getTotalGiven();
        totalTaken = loanRepository.getTotalTaken();

    }


    ///  insertin loan
    public void InsertLoan(Loan loan){
        loanRepository.insertedLoan(loan);
    }

    // Update loan
    public void UpdateLoan(Loan loan){
        loanRepository.updateLoan(loan);
    }

    // Delete loan
    public void DeleteLoan(Loan loan){
        loanRepository.deleteLoan(loan);
    }

    // getting all loans
    public LiveData<List<Loan>> GetAllLoans(){
        return allLoans;
    }

    // for total given
    public LiveData<Double> GetTotalGiven(){
        return totalGiven;
    }

    // for total taken
    public LiveData<Double> GetTotalTaken(){
        return totalTaken;
    }


    public LiveData<Loan> GetLoanById(int loanId) {
        return loanRepository.getLoanById(loanId);
    }

    public LiveData<Loan> GetLastLoan() {
        return loanRepository.getLastLoan();
    }
}