package com.example.todoflow.Activity.TodoModel;

public class AllTaskModel
{
    private int id;
    private String description;
    private String Category;
    private String priority;
    private String DueDate;
    private String CreatedDate;
    private int isCompleted;

    // creating a constructor
    public AllTaskModel(int id, String description, String category, String priority, String dueDate, String createdDate, int isCompleted)
    {
        this.id = id;
        this.description = description;
        this.Category = category;
        this.priority = priority;
        this.DueDate = dueDate;
        this.CreatedDate = createdDate;
        this.isCompleted = isCompleted;

    }
    // Empty Constructor
    public AllTaskModel(){}


    // Getter and Setter Methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
   public String getCategory() {
        return Category;
    }
    public void setCategory(String category) {
        Category = category;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getDueDate() {
        return DueDate;
    }
    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }
    public String getCreatedDate() {
        return CreatedDate;
    }
    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
    public int getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

}
