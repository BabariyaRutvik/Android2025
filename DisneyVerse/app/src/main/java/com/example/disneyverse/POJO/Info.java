package com.example.disneyverse.POJO;

public class Info
{
    private int count;
    private int totalPages;
    private String previousPage;
    private String nextPage;


    // constructor
    public Info(int count, int totalPages, String previousPage, String nextPage) {
        this.count = count;
        this.totalPages = totalPages;
        this.previousPage = previousPage;
        this.nextPage = nextPage;

    }

    // getter and setter
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public String getPreviousPage() {
        return previousPage;
    }
    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }
    public String getNextPage() {
        return nextPage;
    }
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

}
