package com.example.creditrack.CredActivity.AddFragmentDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loan_table")
public class Loan {

    // Primary key for removing duplicate data
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "person_name")
    private String personName;

    @ColumnInfo(name = "loan_type")
    private String loanType;


    @ColumnInfo(name = "amount")
    private double amount;


    @ColumnInfo(name = "date")
    private String date;


    @ColumnInfo(name = "note")
    private String note;


    // now making the constructor of this class
    public Loan(String personName, String loanType, double amount, String date, String note){
        this.personName = personName;
        this.loanType = loanType;
        this.amount = amount;
        this.date = date;
        this.note = note;

    }

        // now making the getter and setter of this class


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
