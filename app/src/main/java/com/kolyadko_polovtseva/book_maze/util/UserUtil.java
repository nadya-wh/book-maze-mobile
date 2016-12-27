package com.kolyadko_polovtseva.book_maze.util;

import com.kolyadko_polovtseva.book_maze.entity.LibraryBook;
import com.kolyadko_polovtseva.book_maze.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nadzeya_Polautsava on 12/23/2016.
 */

public class UserUtil {


    public static User convert(String json) {

        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(json);

            user.setLastName(jsonObject.getString("lastName"));
            user.setFirstName(jsonObject.getString("firstName"));
            if (!jsonObject.isNull("imageUrl")) {
                user.setImageUrl(jsonObject.getString("imageUrl"));
            }

            //libraryBook.setBook(BookUtil.convert(jsonObject.getJSONObject("book")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
