package com.example.creditrack.CredActivity.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class LoanManager
{
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;

     public static final String SHARED_PREF_NAME = "mypref";
     public static final String KEY_ID = "userid";
     public static final String KEY_IS_LOGGEDIN = "isLoggedIn";



     // Constructor
    public LoanManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
      this.editor = sharedPreferences.edit();

    }
    // Login Functionality
    public void  Login(int id){
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        editor.putInt(KEY_ID, id);
        editor.commit();

    }
    // Logout Functionality
    public void Logout(){
        editor.clear();
        editor.commit();
    }

    // if users is Logged in or not
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    // now fetch the user is logged in or not
    public int getUser(){
        return sharedPreferences.getInt(KEY_ID, 0);
    }

}
