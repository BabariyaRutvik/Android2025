package com.example.creditrack.CredActivity.AddFragmentDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoanRepository
{
    private LoanDao loanDao;
    private LiveData<List<Loan>> allLoans;
    private LiveData<Double> totalGiven;
    private LiveData<Double> totalTaken;



    // Executor Service
    ExecutorService executorService;

    public LoanRepository(Application application){
        LoanDatabase database = LoanDatabase.getInstance(application);
        loanDao = database.loanDao();
        allLoans = loanDao.getAllLoans();
        totalGiven = loanDao.getTotalGiven();
        totalTaken = loanDao.getTotalTaken();

        // Initialize the ExecutorService
        executorService = Executors.newSingleThreadExecutor();
    }

    // insertin loan
    public void insertedLoan(Loan loan){
        executorService.execute(() -> loanDao.insert(loan));
    }

    // delete loan
    public void deleteLoan(Loan loan){
        executorService.execute(() -> loanDao.delete(loan));
    }

    // update loan
    public void updateLoan(Loan loan){
        executorService.execute(() -> loanDao.update(loan));
    }

    // now getting all loans
    public LiveData<List<Loan>> getAllLoans(){
        return allLoans;
    }
    // for total given
    public LiveData<Double> getTotalGiven(){
        return totalGiven;
    }
    // for total taken
    public LiveData<Double> getTotalTaken(){
        return totalTaken;
    }

    // Getting loan by id
    public LiveData<Loan> getLoanById(int loanId){
        return loanDao.getLoanById(loanId);
    }

    // Get last inserted loan
    public LiveData<Loan> getLastLoan(){
        return loanDao.getLastLoan();
    }
}