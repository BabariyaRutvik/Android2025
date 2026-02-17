package com.example.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseauth.databinding.ActivityPhoneAuthBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends AppCompatActivity {

    ActivityPhoneAuthBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneAuthBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Sending OTP...");
        progressDialog.setCanceledOnTouchOutside(false);

        // Sending the OTP
        binding.btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOtp();
            }
        });

        // Verifying OTP
        binding.btnVarifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyOtp();
            }
        });
    }

    private void SendOtp() {
        String phoneNumber = binding.editPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.editPhone.setError("Phone Number is required");
            binding.editPhone.requestFocus();
            return;
        }
        
        // Ensure phone number starts with country code, e.g., +91
        if (!phoneNumber.startsWith("+")) {
            Toast.makeText(this, "Please include country code (e.g., +91)", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Sending OTP...");
        progressDialog.show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    SignInWithPhoneAuthCredentials(phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    progressDialog.dismiss();
                    Toast.makeText(PhoneAuthActivity.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    progressDialog.dismiss();
                    verificationId = s;
                    Toast.makeText(PhoneAuthActivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                }
            };

    private void VerifyOtp() {
        String otp = binding.editOtp.getText().toString().trim();

        if (TextUtils.isEmpty(otp)) {
            binding.editOtp.setError("OTP is required");
            binding.editOtp.requestFocus();
            return;
        }
        
        if (otp.length() < 6) {
            binding.editOtp.setError("OTP must be 6 digits");
            binding.editOtp.requestFocus();
            return;
        }

        if (verificationId == null) {
            Toast.makeText(this, "Please send OTP first", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Verifying OTP...");
        progressDialog.show();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        SignInWithPhoneAuthCredentials(credential);
    }

    private void SignInWithPhoneAuthCredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(PhoneAuthActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PhoneAuthActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String error = task.getException() != null ? task.getException().getMessage() : "Unknown Error";
                            Toast.makeText(PhoneAuthActivity.this, "Login Failed: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
