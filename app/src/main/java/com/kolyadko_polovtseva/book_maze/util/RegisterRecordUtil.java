package com.kolyadko_polovtseva.book_maze.util;

import android.util.Log;

import com.kolyadko_polovtseva.book_maze.entity.Author;
import com.kolyadko_polovtseva.book_maze.entity.LibraryBook;
import com.kolyadko_polovtseva.book_maze.entity.RegisterRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nadzeya_Polautsava on 12/10/2016.
 */

public class RegisterRecordUtil {
    public static List<RegisterRecord> convert(String json) {
        List<RegisterRecord> registerRecords = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                RegisterRecord record = new RegisterRecord();
                record.setIdRegister(object.getInt("idRegister"));
                record.setReserveDate(new Date(object.getLong("reserveDate")));
                record.setReturnDeadline(new Date(object.getLong("returnDeadline")));
                record.setLibraryBook(LibraryBookUtil.convert(object.getJSONObject("libraryBook")));
                record.setWasReturned(object.getBoolean("wasReturned"));
                registerRecords.add(record);
            }
        } catch (JSONException e) {
            Log.e("JSON", e.getMessage());
        }
        return registerRecords;
    }
}
