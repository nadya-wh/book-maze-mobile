package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.LibraryBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadzeya_Polautsava on 12/10/2016.
 */

public class LibraryBookUtil {
    public static LibraryBook convert(JSONObject jsonObject) {
        LibraryBook libraryBook = new LibraryBook();
        try {
            libraryBook.setIdLibraryBook(jsonObject.getString("idLibraryBook"));
            libraryBook.setBook(BookUtil.convert(jsonObject.getJSONObject("book")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return libraryBook;
    }
}
