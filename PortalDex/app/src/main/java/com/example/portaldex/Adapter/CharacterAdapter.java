package com.example.portaldex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.portaldex.CharacterActivity.FullCharacterScreen;
import com.example.portaldex.POJO.Character;
import com.example.portaldex.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> charactersList;
    private List<Character> charactersListFull; 
    private Context context;

    public CharacterAdapter(List<Character> charactersList, Context context) {
        this.charactersList = charactersList;
        this.charactersListFull = new ArrayList<>(charactersList);
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_design, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = charactersList.get(position);

        holder.text_name.setText(character.getName());
        String status = character.getStatus();
        holder.text_status.setText(status);

        if (status != null) {
            int color;
            if (status.equalsIgnoreCase("Alive")) {
                color = ContextCompat.getColor(context, R.color.status_alive);
            } else if (status.equalsIgnoreCase("Dead")) {
                color = ContextCompat.getColor(context, R.color.status_dead);
            } else {
                color = ContextCompat.getColor(context, R.color.status_unknown);
            }
            Drawable background = holder.text_status.getBackground();
            if (background != null) {
                background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }
        }

        if (character.getLocation() != null) {
            holder.text_locations.setText(character.getLocation().getName());
        } else {
            holder.text_locations.setText("Unknown Location");
        }

        Glide.with(context)
                .load(character.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image_characters);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FullCharacterScreen.class);
            intent.putExtra("character", character);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return charactersList != null ? charactersList.size() : 0;
    }

    public void filter(String query, String statusFilter) {
        charactersList.clear();
        String lowerCaseQuery = query.toLowerCase().trim();
        for (Character character : charactersListFull) {
            boolean matchesName = character.getName().toLowerCase().contains(lowerCaseQuery);
            boolean matchesStatus = statusFilter.equalsIgnoreCase("All") || character.getStatus().equalsIgnoreCase(statusFilter);
            if (matchesName && matchesStatus) {
                charactersList.add(character);
            }
        }
        notifyDataSetChanged();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView image_characters;
        TextView text_status, text_name, text_label_location, text_locations;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            image_characters = itemView.findViewById(R.id.image_characters);
            text_status = itemView.findViewById(R.id.text_status);
            text_name = itemView.findViewById(R.id.text_name);
            text_label_location = itemView.findViewById(R.id.text_label_location);
            text_locations = itemView.findViewById(R.id.text_locations);
        }
    }
}