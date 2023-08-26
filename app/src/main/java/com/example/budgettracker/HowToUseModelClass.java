package com.example.budgettracker;

public class HowToUseModelClass {
    private  int image;
    private String title;

    public HowToUseModelClass(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HowToUseModelClass{" +
                "image=" + image +
                ", title='" + title + '\'' +
                '}';
    }
}
