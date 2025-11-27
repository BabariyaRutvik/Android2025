package com.example.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Get the SMS message
        Bundle bundle = intent.getExtras();

        // Get the SMS message to the PDUS(Protocol Data Unit)
        Object[] subobj = (Object[]) bundle.get("pdus");

        for (Object obj : subobj) {
            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) obj);
            String phoneNumber = currentMessage.getDisplayOriginatingAddress();
            String message = currentMessage.getDisplayMessageBody();
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Message: " + message);

            Log.d("SMSReciever", "Phone Number: " + phoneNumber);
            Log.d("SMSReciever", "Message: " + message);

            // Send an SMS to the phone number
            SmsManager smsManager = SmsManager.getDefault();


            smsManager.sendTextMessage(phoneNumber, null, "Hello from Android", null, null);
            
        }



    }
}
