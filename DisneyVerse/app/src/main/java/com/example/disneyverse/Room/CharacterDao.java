package com.example.disneyverse.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.disneyverse.POJO.Character;

import java.util.List;

@Dao
public interface CharacterDao
{
    // inserting the character into database or favorite activity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCharacter(Character character);

    // now deleting the data
    @Delete
    public void deleteCharacter(Character character);

    // now fetching the data from database
    @Query("SELECT * FROM favorite_character")
    public List<Character> getFavoriteCharacters();

    // now fetching one data from the mainactvity
    @Query("SELECT EXISTS (SELECT 1 FROM favorite_character WHERE _id = :characterId)")
    public boolean isCharacterFavorite(int characterId);
}
