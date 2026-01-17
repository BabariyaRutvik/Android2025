package com.example.disneyverse.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.disneyverse.Adapter.CharacterAdapter;
import com.example.disneyverse.POJO.Character;
import com.example.disneyverse.Room.CharacterDao;
import com.example.disneyverse.Room.FavoriteDatabase;
import com.example.disneyverse.databinding.ActivityFavoriteBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteActivity extends AppCompatActivity {

    ActivityFavoriteBinding binding;
    CharacterAdapter adapter;
    CharacterDao characterDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        characterDao = FavoriteDatabase.getInstance(this).characterDao();

        binding.favoriteRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.favoriteRecyclerview.setHasFixedSize(true);

        loadFavorites();
    }

    private void loadFavorites() {
        executorService.execute(() -> {
            List<Character> favoriteCharacters = characterDao.getFavoriteCharacters();
            runOnUiThread(() -> {
                if (favoriteCharacters == null || favoriteCharacters.isEmpty()) {
                    binding.emptyStateTextview.setVisibility(View.VISIBLE);
                    binding.favoriteRecyclerview.setVisibility(View.GONE);
                } else {
                    binding.emptyStateTextview.setVisibility(View.GONE);
                    binding.favoriteRecyclerview.setVisibility(View.VISIBLE);
                    adapter = new CharacterAdapter(favoriteCharacters, FavoriteActivity.this);
                    binding.favoriteRecyclerview.setAdapter(adapter);
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list when returning to this activity
        loadFavorites();
    }
}
