package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadzeya_Polautsava on 12/4/2016.
 */

public class BookUtil {

    public static List<Book> convert(String json) {
        List<Book> books = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                books.add(convert(object));
            }
        } catch (JSONException e) {
            Log.e("JSON", e.getMessage());
        }
        return books;
    }
     public static Book convert(JSONObject object) throws JSONException {
         Book book = new Book();
         book.setName(object.getString("name"));
         book.setImageUrl(object.getString("imageUrl"));
         book.setIdBook(object.getInt("idBook"));
         book.setPublishYear(object.getInt("publishYear"));
         book.setDescription(object.getString("description"));
         if (!object.isNull("ebookUrl")) {
             book.setEbookUrl(object.getString("ebookUrl"));
         }
         JSONArray authorsJSON = object.getJSONArray("authors");
         book.setAuthors(AuthorUtil.convert(authorsJSON));
         return book;
     }

}
