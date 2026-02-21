package com.example.producthub.ProductActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.producthub.ProductModel.Product;
import com.example.producthub.R;
import com.example.producthub.databinding.ActivityFullScreenProductBinding;
import com.example.producthub.databinding.UpdateProductDialogBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FullScreenProduct extends AppCompatActivity {

    private ActivityFullScreenProductBinding binding;
    private String productId;
    private FirebaseFirestore firestore;
    private ProgressDialog progressDialog;
    private Product currentProduct;
    private String encodedImage = "";
    private UpdateProductDialogBinding dialogBinding;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        if (dialogBinding != null) {
                            dialogBinding.ivProductUpdate.setImageBitmap(bitmap);
                            dialogBinding.ivProductUpdate.setPadding(0, 0, 0, 0);
                            encodedImage = encodeImage(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullScreenProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();
        productId = getIntent().getStringExtra("PRODUCT_ID");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading product details...");
        progressDialog.setCanceledOnTouchOutside(false);

        if (productId != null) {
            LoadProductDetails();
        }

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.btnUpdate.setOnClickListener(v -> {
            if (currentProduct != null) {
                UpdateData();
            }
        });

        binding.btnDelete.setOnClickListener(v -> {
            if (productId != null) {
                DeleteData();
            }
        });
    }

    private void LoadProductDetails() {
        progressDialog.show();
        firestore.collection("Products").document(productId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    progressDialog.dismiss();
                    if (documentSnapshot.exists()) {
                        currentProduct = documentSnapshot.toObject(Product.class);
                        if (currentProduct != null) {
                            DisplayProductDetails();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(FullScreenProduct.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void DisplayProductDetails() {
        binding.tvName.setText(currentProduct.getName());
        binding.tvCategory.setText(currentProduct.getCategory());
        binding.tvPrice.setText("₹ " + currentProduct.getPrice());
        binding.tvDesc.setText(currentProduct.getDescription());

        if (currentProduct.getImageUrl() != null && !currentProduct.getImageUrl().isEmpty()) {
            byte[] imageBytes = Base64.decode(currentProduct.getImageUrl(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            binding.ivProduct.setImageBitmap(bitmap);
        }
    }

    private void UpdateData() {
        dialogBinding = UpdateProductDialogBinding.inflate(getLayoutInflater());
        encodedImage = currentProduct.getImageUrl(); // Keep current image by default

        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(dialogBinding.getRoot()))
                .setExpanded(true, 1200)
                .create();

        dialogBinding.etNameUpdate.setText(currentProduct.getName());
        dialogBinding.etPriceUpdate.setText(currentProduct.getPrice());
        dialogBinding.etDescUpdate.setText(currentProduct.getDescription());

        String[] categories = {"Electronics", "Clothing", "Home", "Books", "Beauty", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, categories);
        dialogBinding.actvCategoryUpdate.setAdapter(adapter);
        dialogBinding.actvCategoryUpdate.setText(currentProduct.getCategory(), false);

        if (currentProduct.getImageUrl() != null && !currentProduct.getImageUrl().isEmpty()) {
            byte[] imageBytes = Base64.decode(currentProduct.getImageUrl(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            dialogBinding.ivProductUpdate.setImageBitmap(bitmap);
            dialogBinding.ivProductUpdate.setPadding(0, 0, 0, 0);
        }

        dialogBinding.cardImageUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        dialogBinding.btnCancelUpdate.setOnClickListener(v -> dialogPlus.dismiss());

        dialogBinding.btnSaveUpdate.setOnClickListener(v -> {
            String name = dialogBinding.etNameUpdate.getText().toString().trim();
            String price = dialogBinding.etPriceUpdate.getText().toString().trim();
            String desc = dialogBinding.etDescUpdate.getText().toString().trim();
            String category = dialogBinding.actvCategoryUpdate.getText().toString();

            if (name.isEmpty() || price.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("price", price);
            map.put("description", desc);
            map.put("category", category);
            map.put("imageUrl", encodedImage); // Now using the updated encodedImage string

            progressDialog.setMessage("Updating product...");
            progressDialog.show();

            firestore.collection("Products").document(productId)
                    .update(map)
                    .addOnSuccessListener(unused -> {
                        progressDialog.dismiss();
                        dialogPlus.dismiss();
                        Toast.makeText(this, "Product Updated Successfully", Toast.LENGTH_SHORT).show();
                        LoadProductDetails(); // Refresh the screen
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(this, "Update Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        dialogPlus.show();
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 480;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void DeleteData() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Delete Product")
                .setMessage("Are you sure you want to delete this product? This action cannot be undone.")
                .setPositiveButton("Delete", (dialog, which) -> {
                    progressDialog.setMessage("Deleting product...");
                    progressDialog.show();
                    firestore.collection("Products").document(productId)
                            .delete()
                            .addOnSuccessListener(unused -> {
                                progressDialog.dismiss();
                                Toast.makeText(this, "Product Deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(this, "Delete Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
