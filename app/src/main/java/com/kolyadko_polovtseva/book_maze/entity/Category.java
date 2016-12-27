package com.kolyadko_polovtseva.book_maze.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;


public class Category implements Parcelable {
    private Integer idCategory;
    private String name;
    private String imageUrl;
    private Set<Book> books;



    public Category() {
        idCategory = 0;
    }

    protected Category(Parcel in) {
        idCategory = in.readInt();
        name = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!idCategory.equals(category.idCategory)) return false;
        if (!name.equals(category.name)) return false;
        return books != null ? books.equals(category.books) : category.books == null;

    }

    @Override
    public int hashCode() {
        int result = idCategory.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idCategory);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
    }
}