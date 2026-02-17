package com.example.firebaseauth;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.firebaseauth.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    private static final int REQ_CAMERA = 101;
    private static final int REQ_GALLERY = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }

        loadUserData();

        binding.fabCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQ_CAMERA);
        });

        binding.btnUpload.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQ_GALLERY);
        });

        binding.btnEditProfile.setOnClickListener(v -> {
            showEditNameDialog();
        });

        binding.btnLogout.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void loadUserData() {
        database.getReference().child("Users").child(auth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            try {
                                User user = snapshot.getValue(User.class);
                                if (user != null) {
                                    binding.textUserName.setText(user.getFullName());
                                    binding.textUserEmail.setText(user.getEmail());

                                    if (user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
                                        byte[] decodedString = Base64.decode(user.getProfileImage(), Base64.DEFAULT);
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        binding.profileImage.setImageBitmap(decodedByte);
                                    } else {
                                        binding.profileImage.setImageResource(R.drawable.ic_person);
                                    }
                                } else {
                                    Log.e("HomeActivity", "User object is null.");
                                }
                            } catch (Exception e) {
                                Log.e("HomeActivity", "Error parsing user data", e);
                                Toast.makeText(HomeActivity.this, "Error parsing data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                             Log.d("HomeActivity", "No data found for this user in Realtime DB.");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.e("FirebaseDB", "Error loading user data", error.toException());
                        Toast.makeText(HomeActivity.this, "DB Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showEditNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Full Name");

        final EditText input = new EditText(this);
        input.setHint("Enter new name");
        input.setText(binding.textUserName.getText().toString());
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString();
            if (!TextUtils.isEmpty(newName)) {
                database.getReference().child("Users").child(auth.getUid())
                        .child("fullName").setValue(newName)
                        .addOnSuccessListener(aVoid -> Toast.makeText(HomeActivity.this, "Name updated!", Toast.LENGTH_SHORT).show());
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bitmap imageBitmap = null;
            if (requestCode == REQ_CAMERA && data.getExtras() != null) {
                imageBitmap = (Bitmap) data.getExtras().get("data");
            } else if (requestCode == REQ_GALLERY && data.getData() != null) {
                Uri uri = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageBitmap != null) {
                uploadImageToDatabase(imageBitmap);
            }
        }
    }

    private void uploadImageToDatabase(Bitmap bitmap) {
        binding.profileImage.setImageBitmap(bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] bytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

        database.getReference().child("Users").child(auth.getUid())
                .child("profileImage").setValue(base64Image)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(HomeActivity.this, "Profile picture updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, "Update failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
