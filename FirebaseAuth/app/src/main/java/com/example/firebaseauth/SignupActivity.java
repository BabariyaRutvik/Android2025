package com.example.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseauth.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        firebaseAuth = FirebaseAuth.getInstance();
        
        // Using the explicit URL from your screenshot to ensure connection
        firebaseDatabase = FirebaseDatabase.getInstance("https://fir-auth-cd5a8-default-rtdb.firebaseio.com/");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Creating Account...");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.btnSignup.setOnClickListener(v -> CreateAccount());
        
        binding.textSigninLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void CreateAccount() {
        String fullName = binding.editName.getText().toString().trim();
        String phoneNumber = binding.editPhone.getText().toString().trim();
        String email = binding.editEmail.getText().toString().trim();
        String password = binding.editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)) {
            binding.editName.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            binding.editEmail.setError("Required");
            return;
        }
        if (password.length() < 6) {
            binding.editPassword.setError("Password must be at least 6 characters");
            return;
        }

        progressDialog.show();
        binding.btnSignup.setEnabled(false);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        String id = task.getResult().getUser().getUid();
                        User user = new User(fullName, phoneNumber, email, password, id);

                        firebaseDatabase.getReference().child("Users").child(id).setValue(user)
                                .addOnCompleteListener(dbTask -> {
                                    progressDialog.dismiss();
                                    if (dbTask.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "SignUp Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, SignInActivity.class));
                                        finish();
                                    } else {
                                        Log.e("FirebaseDB", "Error saving user", dbTask.getException());
                                        Toast.makeText(SignupActivity.this, "Database Error: " + dbTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        binding.btnSignup.setEnabled(true);
                                    }
                                });
                    } else {
                        progressDialog.dismiss();
                        binding.btnSignup.setEnabled(true);
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignupActivity.this, "Email already exists. Try logging in.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Auth Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
