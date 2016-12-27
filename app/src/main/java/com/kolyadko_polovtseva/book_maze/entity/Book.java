package com.kolyadko_polovtseva.book_maze.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DaryaKolyadko on 26.11.2016.
 */

public class Book implements Parcelable {
    private Integer idBook;
    private String name;
    private Integer pageNum;
    private Integer publishYear;
    private String description;
    private String ebookUrl;
    private String imageUrl;
    private Category category;
    private PublishHouse publishHouse;
    private Set<Author> authors;

    public Book() {
    }

    protected Book(Parcel in) {
        idBook = in.readInt();
        name = in.readString();
        description = in.readString();
        ebookUrl = in.readString();
        imageUrl = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        List<Author> authorsList = new ArrayList<>();
        in.readTypedList(authorsList, Author.CREATOR);
        authors = new HashSet<>(authorsList);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEbookUrl() {
        return ebookUrl;
    }

    public void setEbookUrl(String ebookUrl) {
        this.ebookUrl = ebookUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PublishHouse getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(PublishHouse publishHouse) {
        this.publishHouse = publishHouse;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!idBook.equals(book.idBook)) return false;
        if (!name.equals(book.name)) return false;
        if (pageNum != null ? !pageNum.equals(book.pageNum) : book.pageNum != null) return false;
        if (publishYear != null ? !publishYear.equals(book.publishYear) : book.publishYear != null)
            return false;
        if (description != null ? !description.equals(book.description) : book.description != null)
            return false;
        if (ebookUrl != null ? !ebookUrl.equals(book.ebookUrl) : book.ebookUrl != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(book.imageUrl) : book.imageUrl != null)
            return false;
        if (category != null ? !category.equals(book.category) : book.category != null)
            return false;
        if (publishHouse != null ? !publishHouse.equals(book.publishHouse) : book.publishHouse != null)
            return false;
        return authors != null ? authors.equals(book.authors) : book.authors == null;

    }

    @Override
    public int hashCode() {
        int result = idBook.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (pageNum != null ? pageNum.hashCode() : 0);
        result = 31 * result + (publishYear != null ? publishYear.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ebookUrl != null ? ebookUrl.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (publishHouse != null ? publishHouse.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idBook);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(ebookUrl);
        parcel.writeString(imageUrl);
        parcel.writeParcelable(category, i);
        parcel.writeTypedList(new ArrayList<Parcelable>(authors));
    }
}