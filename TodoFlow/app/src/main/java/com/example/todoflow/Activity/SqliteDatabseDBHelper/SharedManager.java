package com.example.todoflow.Activity.SqliteDatabseDBHelper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManager
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_PREF_NAME = "mypref";
   public static final String KEY_USER_ID = "userid";
  public static final String KEY_IS_LOGGED_IN = "isloggedin";


  // now creating constructor
    public SharedManager(Context context)
    {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();


    }
    public void LogInSession(int userId) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USER_ID, String.valueOf(userId));
        editor.commit();
    }
    public void LogOutSession() {
        editor.clear();
        editor.commit();
    }
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);

    }
}
