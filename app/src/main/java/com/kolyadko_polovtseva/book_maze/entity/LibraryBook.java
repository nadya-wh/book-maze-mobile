package com.kolyadko_polovtseva.book_maze.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by DaryaKolyadko on 26.11.2016.
 */
public class LibraryBook implements Parcelable {

    private String idLibraryBook;
    private Book book;
    private Set<RegisterRecord> registerRecords;

    public LibraryBook() {
    }

    protected LibraryBook(Parcel in) {
        idLibraryBook = in.readString();
        book = in.readParcelable(Book.class.getClassLoader());
    }

    public static final Creator<LibraryBook> CREATOR = new Creator<LibraryBook>() {
        @Override
        public LibraryBook createFromParcel(Parcel in) {
            return new LibraryBook(in);
        }

        @Override
        public LibraryBook[] newArray(int size) {
            return new LibraryBook[size];
        }
    };

    public String getIdLibraryBook() {
        return idLibraryBook;
    }

    public void setIdLibraryBook(String idLibraryBook) {
        this.idLibraryBook = idLibraryBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<RegisterRecord> getRegisterRecords() {
        return registerRecords;
    }

    public void setRegisterRecords(Set<RegisterRecord> registerRecords) {
        this.registerRecords = registerRecords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idLibraryBook);
        parcel.writeParcelable(book, i);
    }
}