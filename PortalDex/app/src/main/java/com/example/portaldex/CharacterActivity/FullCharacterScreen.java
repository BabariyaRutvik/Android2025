package com.example.portaldex.CharacterActivity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.portaldex.POJO.Character;
import com.example.portaldex.R;
import com.example.portaldex.databinding.ActivityFullCharacterScreenBinding;

public class FullCharacterScreen extends AppCompatActivity {
    ActivityFullCharacterScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullCharacterScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup Toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Character Details");
        }

        // Receiving Data from Intent - KEY MATCHED TO "character"
        Character character = (Character) getIntent().getSerializableExtra("character");

        if (character != null) {
            populateData(character);
        }
    }

    private void populateData(Character character) {
        // Name and Image
        binding.textName.setText(character.getName());
        
        // Load actual character image
        Glide.with(this)
                .load(character.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.imageCharacters);

        // Status and Color
        String status = character.getStatus();
        binding.textStatus.setText(status);
        if (status != null) {
            int color;
            if (status.equalsIgnoreCase("Alive")) {
                color = ContextCompat.getColor(this, R.color.status_alive);
            } else if (status.equalsIgnoreCase("Dead")) {
                color = ContextCompat.getColor(this, R.color.status_dead);
            } else {
                color = ContextCompat.getColor(this, R.color.status_unknown);
            }
            Drawable background = binding.textStatus.getBackground();
            if (background != null) {
                background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }
        }

        // Other Details
        binding.textSpecies.setText("Species: " + character.getSpecies());
        binding.textGender.setText("Gender: " + character.getGender());

        if (character.getOrigin() != null) {
            binding.textOrigin.setText("Origin: " + character.getOrigin().getName());
        } else {
            binding.textOrigin.setText("Origin: Unknown");
        }

        if (character.getLocation() != null) {
            binding.textLocation.setText("Last Location: " + character.getLocation().getName());
        } else {
            binding.textLocation.setText("Last Location: Unknown");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}