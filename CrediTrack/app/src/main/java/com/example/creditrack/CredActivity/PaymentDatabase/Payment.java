package com.example.creditrack.CredActivity.PaymentDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payments")
public class Payment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int loanId;


    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "note")
    private String note;


    @ColumnInfo(name = "payment_type")
    private String paymentType;


    public Payment(int loanId, double amount, String date, String note, String paymentType){
        this.loanId = loanId;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.paymentType = paymentType;

    }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanId() {
        return loanId;
    }
    public void setLoanId(int loanId) {
        this.loanId = loanId;
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

    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
