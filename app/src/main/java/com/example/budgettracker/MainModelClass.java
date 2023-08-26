package com.example.budgettracker;

public class MainModelClass {
    private  int imageId;
    private String title;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainModelClass() {
    }

    public MainModelClass(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "MainModelClass{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                '}';
    }
}
