package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;

import com.kolyadko_polovtseva.book_maze.entity.Author;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nadzeya_Polautsava on 12/4/2016.
 */

public class AuthorUtil {
    public static List<Author> convert(String json) {
        List<Author> authors = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Author author = new Author();
                author.setFirstName(object.getString("firstName"));
                author.setLastName(object.getString("lastName"));
                author.setIdAuthor(object.getInt("idAuthor"));
                authors.add(author);
            }
        } catch (JSONException e) {
            Log.e("JSON", e.getMessage());
        }
        return authors;
    }

    public static Set<Author> convert(JSONArray jsonArray) {
        Set<Author> authors = new HashSet<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Author author = new Author();
                author.setFirstName(object.getString("firstName"));
                author.setLastName(object.getString("lastName"));
                author.setIdAuthor(object.getInt("idAuthor"));
                authors.add(author);
            }
        } catch (JSONException e) {
            Log.e("JSON", e.getMessage());
        }
        return authors;

    }

    public static String toString(Set<Author> authors) {
        String names = "";
        for (Author a : authors) {
            names += a.getFirstName() + " " + a.getLastName() + "\n";
        }
        return names;
    }
}
