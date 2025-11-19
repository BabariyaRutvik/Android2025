package com.example.statusbarnotification;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImplecitIntent extends AppCompatActivity {

    AppCompatButton btn_dial;
    AppCompatButton btn_email;
    AppCompatButton btn_message;
    AppCompatButton btn_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implecit_intent);


        btn_dial = findViewById(R.id.btndial);
        btn_email = findViewById(R.id.btn_email);
        btn_message = findViewById(R.id.btn_message);
        btn_share = findViewById(R.id.btn_share);

        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inDial = new Intent(Intent.ACTION_CALL);
                inDial.setData(Uri.parse("tel: +916351202084"));
                startActivity(inDial);
            }
        });
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for email
                Intent iEmail = new Intent(Intent.ACTION_SEND);
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"babariyarutvik1013@gmail.com"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,"My Queries");
                iEmail.putExtra(Intent.EXTRA_TEXT,"Please Resolve my issue");
                startActivity(Intent.createChooser(iEmail, "Email Via"));


            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMsg = new Intent(Intent.ACTION_SENDTO);
                iMsg.setData(Uri.parse("smsto: " +Uri.encode("+916351202084")));
                iMsg.putExtra("sms_body","Please Solve this issue");
                startActivity(iMsg);
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for share the app
                Intent iShare = new Intent(Intent.ACTION_SEND);
                iShare.setType("text/plain");
                iShare.putExtra(Intent.EXTRA_TEXT,"Download this Amazing App");
                startActivity(Intent.createChooser(iShare,"Share Via"));

            }
        });
    }
}