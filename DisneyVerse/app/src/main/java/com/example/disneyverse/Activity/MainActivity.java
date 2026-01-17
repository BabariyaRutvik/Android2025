package com.example.disneyverse.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disneyverse.Adapter.CharacterAdapter;
import com.example.disneyverse.Client.CharacterClient;
import com.example.disneyverse.Interface.CharacterInterface;
import com.example.disneyverse.POJO.Character;
import com.example.disneyverse.POJO.DisneyResponce;
import com.example.disneyverse.R;
import com.example.disneyverse.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CharacterAdapter adapter;
    CharacterInterface characterInterface;

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    // --- PERSISTENCE CONSTANTS ---
    private static final String PREFS_NAME = "DisneyPrefs";
    private static final String KEY_CURRENT_PAGE = "current_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // --- LOAD SAVED PAGE ---
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        currentPage = prefs.getInt(KEY_CURRENT_PAGE, 1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.characterRecyclerview.setLayoutManager(layoutManager);
        binding.characterRecyclerview.setHasFixedSize(true);

        adapter = new CharacterAdapter(new ArrayList<>(), this);
        binding.characterRecyclerview.setAdapter(adapter);

        // --- PAGINATION CODE START ---
        binding.characterRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadNextPage();
                    }
                }
            }
        });
        // --- PAGINATION CODE END ---

        // --- SWIPE TO REFRESH CODE START ---
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            // When refreshing, we reset to a RANDOM page so you can see the data actually changes!
            currentPage = new Random().nextInt(50) + 1; 
            isLastPage = false;
            
            // Save the new random page to disk
            saveCurrentPage(currentPage);
            
            if (adapter != null) {
                adapter.clearData();
            }
            
            Toast.makeText(this, "Refreshing page: " + currentPage, Toast.LENGTH_SHORT).show();
            FetchCharacters(currentPage);
        });
        
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.blue_button);
        // --- SWIPE TO REFRESH CODE END ---

        // Start by fetching the page we loaded from SharedPreferences
        FetchCharacters(currentPage);
        SearchCharacters();
    }

    private void saveCurrentPage(int page) {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putInt(KEY_CURRENT_PAGE, page)
                .apply();
    }

    private void loadNextPage() {
        currentPage++;
        saveCurrentPage(currentPage); // Save progress as we paginate
        FetchCharacters(currentPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void FetchCharacters(int page) {
        isLoading = true;
        
        if (!binding.swipeRefreshLayout.isRefreshing()) {
            binding.characterProgressIndicator.setVisibility(View.VISIBLE);
        }

        characterInterface = CharacterClient.getClient().create(CharacterInterface.class);

        Call<DisneyResponce> call = characterInterface.getCharacters(page);

        call.enqueue(new Callback<DisneyResponce>() {
            @Override
            public void onResponse(Call<DisneyResponce> call, Response<DisneyResponce> response) {
                isLoading = false;
                binding.characterProgressIndicator.setVisibility(View.GONE);
                
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    binding.swipeRefreshLayout.setRefreshing(false);
                }

                if (response.isSuccessful() && response.body() != null) {
                    List<Character> characters = response.body().getData();
                    if (characters != null && !characters.isEmpty()) {
                        adapter.addData(characters);
                    } else {
                        isLastPage = true;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load characters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DisneyResponce> call, Throwable throwable) {
                isLoading = false;
                binding.characterProgressIndicator.setVisibility(View.GONE);
                
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    binding.swipeRefreshLayout.setRefreshing(false);
                }

                Log.e("MainActivity", "Error: " + throwable.getMessage());
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SearchCharacters() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter != null) {
                    adapter.filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    adapter.filter(newText);
                }
                return false;
            }
        });
    }
}
