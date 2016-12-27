package com.kolyadko_polovtseva.book_maze.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadzeya_Polautsava on 12/4/2016.
 */

public class CloudinaryUtil {

    private static CloudinaryUtil instance = new CloudinaryUtil();

    private Cloudinary cloudinary;

    public CloudinaryUtil() {
        Map config = new HashMap();
        config.put("cloud_name", "daryakolyadkocloud");
        config.put("api_key", "675181477225876");
        config.put("api_secret", "tUhbdOqTuH2h3omJQSEGi5kC6Qs");
        cloudinary = new Cloudinary(config);
    }

    public static CloudinaryUtil getInstance() {
        return instance;
    }

    public String generateImageUrl(String name, Integer width, Integer height) {
        return cloudinary.url().transformation(new Transformation().width(width).height(height).crop("fill")).generate(name);

    }


}
