package com.example.drawable;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        /*
            Single Button Dialog Box
         */

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Terms & Condition");
        alertDialog.setIcon(R.drawable.baseline_info_24);
        alertDialog.setMessage("Have You Read All Terms & Condition");


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I Have Read ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, "Yes, You Can Proceed now.", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();

       /*
         Delete dialog
        */

//        AlertDialog.Builder builder= new AlertDialog.Builder(AlertDialogActivity.this);
//
//        builder.setTitle("Delete?");
//        builder.setIcon(R.drawable.baseline_delete_24);
//        builder.setMessage("Are You Sure You Want to Delete?");
//
//        /*
//         Setting up Positive button
//         */
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AlertDialogActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
//            }
//        });
//        /*
//         Setting up Nagative Button
//         */
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AlertDialogActivity.this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.show();


    }
}

//    @Override
//    public void onBackPressed() {
//         /*
//          Three Button Alert Dialog
//         */
//
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//
//        builder1.setTitle("Exit?");
//        builder1.setMessage("Are you sure You Want to Exist?");
//        builder1.setIcon(R.drawable.baseline_exit_to_app_24);
//
//
//        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                AlertDialogActivity.super.onBackPressed();
//            }
//        });
//        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AlertDialogActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder1.setNeutralButton("Cencle", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AlertDialogActivity.this, "Operation Cencle!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder1.show();
//
//    }
//}