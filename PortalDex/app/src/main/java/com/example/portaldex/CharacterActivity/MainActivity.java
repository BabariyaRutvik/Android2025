package com.example.portaldex.CharacterActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.portaldex.Adapter.CharacterAdapter;
import com.example.portaldex.Client.CharacterClient;
import com.example.portaldex.Interface.CharacterInterface;
import com.example.portaldex.POJO.APIResponse;
import com.example.portaldex.POJO.Character;
import com.example.portaldex.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CharacterAdapter characterAdapter;
    CharacterInterface characterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.characterRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.characterRecyclerview.setHasFixedSize(true);

        FetchCharacters();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilters();
                return true;
            }
        });

        binding.chipGroupFilter.setOnCheckedStateChangeListener((group, checkedIds) -> {
            applyFilters();
        });
    }

    private void applyFilters() {
        if (characterAdapter != null) {
            String query = binding.searchView.getQuery().toString();
            String status = getSelectedStatus();
            characterAdapter.filter(query, status);
        }
    }

    private String getSelectedStatus() {
        int checkedId = binding.chipGroupFilter.getCheckedChipId();
        if (checkedId == com.example.portaldex.R.id.chip_alive) return "Alive";
        if (checkedId == com.example.portaldex.R.id.chip_dead) return "Dead";
        if (checkedId == com.example.portaldex.R.id.chip_unknown) return "Unknown";
        return "All";
    }

    private void FetchCharacters() {
        binding.progressbarCharacters.setVisibility(View.VISIBLE);

        characterInterface = CharacterClient.getClient().create(CharacterInterface.class);
        Call<APIResponse> call = characterInterface.getCharacters(1);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                binding.progressbarCharacters.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Character> characterList = response.body().getResults();
                    
                    if (characterList != null && !characterList.isEmpty()) {
                        setupAdapter(characterList);
                    } else {
                        Toast.makeText(MainActivity.this, "No characters found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Server Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                binding.progressbarCharacters.setVisibility(View.GONE);
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Network Failed. Check Internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter(List<Character> list) {
        characterAdapter = new CharacterAdapter(list, MainActivity.this);
        binding.characterRecyclerview.setAdapter(characterAdapter);
    }
}
