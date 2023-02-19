package com.rajesh.sports_news;

public class Sport {

    String title;
    String info;

    private final int imageResource;

    public Sport(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }
}
