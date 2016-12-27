package com.kolyadko_polovtseva.book_maze.network;

import android.util.Log;

import com.kolyadko_polovtseva.book_maze.entity.Response;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Nadzeya_Polautsava on 10/25/2016.
 */

public class WebServiceCall {

    public static final String TAG = "WebServiceCall";
    private static final String BASE_URL = "https://peaceful-cove-43829.herokuapp.com";
    public static final String POST_METHOD_NAME = "POST";
    public static final String GET_METHOD_NAME = "GET";

    public static Response postTo(String additionalUrl, String urlParameters, String methodName) {
        Response response = null;
        try {
            URL url = new URL(BASE_URL + additionalUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(methodName);
            connection.setDoOutput(true);
            OutputStream wr = connection.getOutputStream();

            //DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            wr.write(urlParameters.getBytes("UTF-8"));
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                String body = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    body += line;
                }
                response = new Response(responseCode, body);
            } else {
                response = new Response(responseCode);
            }
        } catch (Exception e) {
            Log.d(TAG, "Background", e);
        }
        return response;
    }

}
