package com.example.movies_eirian_tta6.models;

import java.util.List;

public class MovieResponseObject {
    /*
    * Sample object from API
    * {
        "dates": {
            "maximum": "2022-08-12",
            "minimum": "2022-06-25"
        },
        "page": 1,
        "results": [{},{}...]
     */

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieResponseObject{" +
                "results=" + results +
                '}';
    }
}
