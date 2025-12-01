package com.example.creditrack.CredActivity.AddFragmentDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.creditrack.CredActivity.PaymentDatabase.Payment;
import com.example.creditrack.CredActivity.PaymentDatabase.PaymentDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Loan.class, Payment.class}, version = 2, exportSchema = false)
public abstract class LoanDatabase extends RoomDatabase {
    private static LoanDatabase instance;
    public abstract LoanDao loanDao();
    public abstract PaymentDao paymentDao();


    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);



    public static synchronized LoanDatabase getInstance(Context context){
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LoanDatabase.class, "loan_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
