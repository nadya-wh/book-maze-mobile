package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;
import android.widget.ListAdapter;

import com.kolyadko_polovtseva.book_maze.entity.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadzeya_Polautsava on 12/2/2016.
 */

public class CategoryUtil {

    public static List<Category> convert(String json) {
        List<Category> categories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Category category = new Category();
                category.setIdCategory(object.getInt("idCategory"));
                category.setName(object.getString("name"));
                category.setImageUrl(object.getString("imageUrl"));
                categories.add(category);
            }
        } catch (JSONException e) {
            Log.e("JSON", e.getMessage());
        }
        return categories;

    }
}
