package com.example.tripease.TripActivity.TripModel;

public class Top10Model {

    private int image;
    private String Name;
    private String Location;
    private String best_time;
    private String id;

    // Creating constructor for the Top10Model class

    public Top10Model(int image, String Name, String Location, String best_time) {
        this.image = image;
        this.Name = Name;
        this.Location = Location;
        this.best_time = best_time;
        this.id = Name + "_" + Location; // Auto-generate an ID
    }

    // gatter and setter methods for the Top10Model class

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
   public String getName() {
       return Name;
   }

   public void setName(String Name) {
       this.Name = Name;
   }

   public String getLocation() {
       return Location;
   }

   public void setLocation(String Location) {
       this.Location = Location;
   }

   public String getBest_time() {
       return best_time;
   }

   public void setBest_time(String best_time) {
       this.best_time = best_time;
   }
   public String getId() {
       return id;
   }

   public void setId(String id) {
       this.id = id;
   }

}
