package com.example.disneyverse.Activity;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.disneyverse.POJO.Character;
import com.example.disneyverse.R;
import com.example.disneyverse.databinding.ActivityFullScreenCharactersBinding;

import java.util.List;

public class FullScreenCharacters extends AppCompatActivity {

    ActivityFullScreenCharactersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullScreenCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the character object from the intent
        Character character = (Character) getIntent().getSerializableExtra("character_data");

        if (character != null) {
            displayCharacterDetails(character);
        }

        // Setup back button
        binding.detailToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void displayCharacterDetails(Character character) {
        // Set Name
        binding.fullCharacterNameTextview.setText(character.getName());
        binding.detailToolbar.setTitle(character.getName());

        // Set Image
        Glide.with(this)
                .load(character.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.fullCharacterImage);

        // Set Films
        setupListText(character.getFilms(), binding.fullFilmsTextview);

        // Set TV Shows
        setupListText(character.getTvShows(), binding.fullTvshowsTextview);

        // Set Park Attractions
        setupListText(character.getParkAttractions(), binding.fullParksTextview);
    }

    private void setupListText(List<String> list, android.widget.TextView textView) {
        if (list != null && !list.isEmpty()) {
            textView.setText(TextUtils.join(", ", list));
        } else {
            textView.setText("None");
        }
    }
}
