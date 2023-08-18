package com.example.movies_eirian_tta6.models;

public class ImageConfigurationInfo {
    /*
    Sample object from API
    * "images": {
        "base_url": "http://image.tmdb.org/t/p/",
        "secure_base_url": "https://image.tmdb.org/t/p/",
        "backdrop_sizes": ["w300", "w780", "w1280", "original"],
        "logo_sizes": ["w45", "w92", "w154", "w185", "w300", "w500", "original"],
        "poster_sizes": ["w92", "w154", "w185", "w342", "w500", "w780", "original"],
        "profile_sizes": ["w45", "w185", "h632", "original"],
        "still_sizes": ["w92", "w185", "w300", "original"]
      },
     */
    private String secure_base_url;

    public String getSecure_base_url() {
        return secure_base_url;
    }

    @Override
    public String toString() {
        return "ImageConfigurationInfo{" +
                "secure_base_url='" + secure_base_url + '\'' +
                '}';
    }
}
