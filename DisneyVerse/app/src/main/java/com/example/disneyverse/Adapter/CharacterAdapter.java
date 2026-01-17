package com.example.disneyverse.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.disneyverse.Activity.FullScreenCharacters;
import com.example.disneyverse.POJO.Character;
import com.example.disneyverse.R;
import com.example.disneyverse.Room.CharacterDao;
import com.example.disneyverse.Room.FavoriteDatabase;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private List<Character> characters;
    private List<Character> charactersFull;
    private final Context context;
    private CharacterDao characterDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CharacterAdapter(List<Character> characters, Context context) {
        this.characters = new ArrayList<>(characters);
        this.context = context;
        this.charactersFull = new ArrayList<>(characters);
        this.characterDao = FavoriteDatabase.getInstance(context).characterDao();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disney_character_dedign, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characters.get(position);
        
        holder.characterNameTextView.setText(character.getName());

        if (character.getFilms() != null && !character.getFilms().isEmpty()) {
            String films = TextUtils.join(", ", character.getFilms());
            holder.characterFilmsTextView.setText("Films: " + films);
        } else {
            holder.characterFilmsTextView.setText("Films: None");
        }

        Glide.with(context)
                .load(character.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.characterImageView);

        updateFavoriteIcon(holder, character);

        holder.moreInfoButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, FullScreenCharacters.class);
            intent.putExtra("character_data", character);
            context.startActivity(intent);
        });

        holder.favoriteImageView.setOnClickListener(v -> {
            executorService.execute(() -> {
                boolean isFav = characterDao.isCharacterFavorite(character.getId());
                if (isFav) {
                    characterDao.deleteCharacter(character);
                    holder.itemView.post(() -> {
                        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border);
                        Toast.makeText(context, character.getName() + " removed from favorites", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    characterDao.insertCharacter(character);
                    holder.itemView.post(() -> {
                        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_filled);
                        Toast.makeText(context, character.getName() + " added to favorites", Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });
    }

    public void updateFavoriteIcon(CharacterViewHolder holder, Character character) {
        executorService.execute(() -> {
            boolean isFav = characterDao.isCharacterFavorite(character.getId());
            holder.itemView.post(() -> {
                if (isFav) {
                    holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_filled);
                } else {
                    holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border);
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    // --- PAGINATION CODE START ---
    public void addData(List<Character> newCharacters) {
        int startPos = this.characters.size();
        this.characters.addAll(newCharacters);
        this.charactersFull.addAll(newCharacters);
        notifyItemRangeInserted(startPos, newCharacters.size());
    }
    // --- PAGINATION CODE END ---

    // --- SWIPE TO REFRESH CODE START ---
    /**
     * Clears all data from the adapter.
     * Useful for refreshing the list from scratch.
     */
    public void clearData() {
        int size = this.characters.size();
        this.characters.clear();
        this.charactersFull.clear();
        notifyItemRangeRemoved(0, size);
    }
    // --- SWIPE TO REFRESH CODE END ---

    public void filter(String text) {
        List<Character> filteredList = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            filteredList.addAll(charactersFull);
        } else {
            String filterPattern = text.toLowerCase().trim();
            for (Character character : charactersFull) {
                if (character.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(character);
                }
            }
        }
        this.characters = filteredList;
        notifyDataSetChanged();
    }
}

class CharacterViewHolder extends RecyclerView.ViewHolder {
    CircleImageView characterImageView;
    TextView characterNameTextView;
    TextView characterFilmsTextView;
    MaterialButton moreInfoButton;
    ImageView favoriteImageView;

    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        characterImageView = itemView.findViewById(R.id.character_imageview);
        characterNameTextView = itemView.findViewById(R.id.character_name_textview);
        characterFilmsTextView = itemView.findViewById(R.id.character_films_textview);
        moreInfoButton = itemView.findViewById(R.id.more_info_button);
        favoriteImageView = itemView.findViewById(R.id.favorite_imageview);
    }
}
