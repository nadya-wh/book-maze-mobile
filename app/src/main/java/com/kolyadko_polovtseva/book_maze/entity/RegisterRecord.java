package com.kolyadko_polovtseva.book_maze.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DaryaKolyadko on 26.11.2016.
 */
public class RegisterRecord implements Parcelable {

    private Integer idRegister;
    private LibraryBook libraryBook;
    private User user;

    private Date reserveDate;

    private Date returnDeadline;
    private Boolean wasReturned;

    public RegisterRecord() {
    }

    protected RegisterRecord(Parcel in) {
        idRegister = in.readInt();
        libraryBook = in.readParcelable(LibraryBook.class.getClassLoader());
        reserveDate = new Date(in.readLong());
        returnDeadline = new Date(in.readLong());
        wasReturned = in.readInt() == 1;
    }

    public static final Creator<RegisterRecord> CREATOR = new Creator<RegisterRecord>() {
        @Override
        public RegisterRecord createFromParcel(Parcel in) {
            return new RegisterRecord(in);
        }

        @Override
        public RegisterRecord[] newArray(int size) {
            return new RegisterRecord[size];
        }
    };

    public Integer getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(Integer idRegister) {
        this.idRegister = idRegister;
    }

    public LibraryBook getLibraryBook() {
        return libraryBook;
    }

    public void setLibraryBook(LibraryBook libraryBook) {
        this.libraryBook = libraryBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Date getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(Date returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public Boolean getWasReturned() {
        return wasReturned;
    }

    public void setWasReturned(Boolean wasReturned) {
        this.wasReturned = wasReturned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idRegister);
        parcel.writeParcelable(libraryBook, i);
        parcel.writeLong(reserveDate.getTime());
        parcel.writeLong(returnDeadline.getTime());
        parcel.writeInt(wasReturned ? 1 : 0);
    }
}