package com.example.realmdatabase.RealmDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realmdatabase.R;

public class DeleteCustom
{
    public  static void show(Context context, String message){
        View view = LayoutInflater.from(context).inflate(R.layout.delete_data, null);
        TextView textView = view.findViewById(R.id.text_delete_data);
        textView.setText(message);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();

    }
}
