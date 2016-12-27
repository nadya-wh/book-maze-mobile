package com.kolyadko_polovtseva.book_maze.row_model;

import android.graphics.Bitmap;

/**
 * Created by User on 20.12.2015.
 */
public class RowModel {
    private String name;
    private Boolean value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */
    private String imageUrl;
    private Bitmap picture;


    public RowModel(String name, Boolean value, String imageUrl) {
        this.name = name;
        this.value = value;
        this.imageUrl = imageUrl;
    }


    public RowModel(String name, Boolean value, String imageUrl,
                    Bitmap picture) {
        this.name = name;
        this.value = value;
        this.imageUrl = imageUrl;
        this.picture = picture;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isValue() {
        return value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
