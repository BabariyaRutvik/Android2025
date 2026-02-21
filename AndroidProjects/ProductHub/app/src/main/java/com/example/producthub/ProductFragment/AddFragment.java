package com.example.producthub.ProductFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.producthub.ProductModel.Product;
import com.example.producthub.R;
import com.example.producthub.databinding.FragmentAddBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private FirebaseFirestore firestore;
    private ProgressDialog progressDialog;
    private String encodedImage = "";

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // firestore initializing
        firestore = FirebaseFirestore.getInstance();

        // progress dialog
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Adding product...");
        progressDialog.setCanceledOnTouchOutside(false);

        // setup category spinner
        String[] categories = {"Electronics", "Clothing", "Home", "Books", "Beauty", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, categories);
        binding.etCategory.setAdapter(adapter);

        // image picker
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                            binding.ivProduct.setImageBitmap(bitmap);
                            binding.ivProduct.setVisibility(View.VISIBLE);
                            binding.placeholderLayout.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        binding.cardImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intentActivityResultLauncher.launch(intent);
        });

        binding.btnSave.setOnClickListener(v -> saveProduct());
    }

    private String encodeImage(Bitmap bitmap) {
        // Scale down the bitmap to keep it under Firestore document size limits
        int previewWidth = 480;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void saveProduct() {
        String name = binding.etName.getText().toString().trim();
        String price = binding.etPrice.getText().toString().trim();
        String category = binding.etCategory.getText().toString().trim();
        String desc = binding.etDesc.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            binding.etName.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(price)) {
            binding.etPrice.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(category)) {
            binding.etCategory.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(encodedImage)) {
            Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        String productId = UUID.randomUUID().toString();
        Product product = new Product(productId, name, desc, price, encodedImage, category, System.currentTimeMillis());

        firestore.collection("Products").document(productId)
                .set(product)
                .addOnSuccessListener(aVoid -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Product added successfully!", Toast.LENGTH_SHORT).show();
                    if (getActivity() != null) {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
