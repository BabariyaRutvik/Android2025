package com.example.disneyverse.POJO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "favorite_character")
public class Character implements Serializable
{
    // https://api.disneyapi.dev/character
    @PrimaryKey
    private int _id;
    private String name;
    private String imageUrl;
    private String url;

    private List<String> films;

    private List<String> shortFilms;

    private List<String> tvShows;

    private List<String> videoGames;

    private List<String> parkAttractions;
    private List<String> allies;
    private List<String> enemies;


    // constructor
    public Character(int id, String name, String imageUrl, String url, List<String> films, List<String> shortFilms, List<String> tvShows, List<String> videoGames, List<String> parkAttractions, List<String> allies, List<String> enemies) {
        this._id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.url = url;
        this.films = films;
        this.shortFilms = shortFilms;
        this.tvShows = tvShows;
        this.videoGames = videoGames;
        this.parkAttractions = parkAttractions;
        this.allies = allies;
        this.enemies = enemies;
    }
    public Character() {
    }


    // getters and setters
    public int getId() {
        return _id;
    }
    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFilms() {
        return films;
    }
    public void setFilms(List<String> films) {
        this.films = films;
    }

   public List<String> getShortFilms() {
        return shortFilms;
    }
    public void setShortFilms(List<String> shortFilms) {
        this.shortFilms = shortFilms;
    }

    public List<String> getTvShows() {
        return tvShows;
    }
    public void setTvShows(List<String> tvShows) {
        this.tvShows = tvShows;
    }

    public List<String> getVideoGames() {
        return videoGames;
    }
    public void setVideoGames(List<String> videoGames) {
        this.videoGames = videoGames;
    }
    public List<String> getParkAttractions() {
        return parkAttractions;
    }
    public void setParkAttractions(List<String> parkAttractions) {
        this.parkAttractions = parkAttractions;
    }

    public List<String> getAllies() {
        return allies;
    }
    public void setAllies(List<String> allies) {
        this.allies = allies;
    }

    public List<String> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<String> enemies) {
        this.enemies = enemies;
    }

}
