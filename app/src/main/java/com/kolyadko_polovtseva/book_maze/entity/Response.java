package com.kolyadko_polovtseva.book_maze.entity;

import org.json.JSONObject;

/**
 * Created by Nadzeya_Polautsava on 10/25/2016.
 */

public class Response {
    private Integer responseCode;
    private String body;

    public Response(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Response(Integer responseCode, String body) {
        this.responseCode = responseCode;
        this.body = body;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
