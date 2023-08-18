package com.example.movies_eirian_tta6.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    /*
    * Sample object from the API
    *
    {
        "adult": false,
        "backdrop_path": "/p1F51Lvj3sMopG948F5HsBbl43C.jpg",
        "genre_ids": [28, 12, 14],
        "id": 616037,
        "original_language": "en",
        "original_title": "Thor: Love and Thunder",
        "overview": "After his retirement is interrupted by Gorr the God Butcher, a galactic killer who seeks the extinction of the gods, Thor enlists the help of King Valkyrie, Korg, and ex-girlfriend Jane Foster, who now inexplicably wields Mjolnir as the Mighty Thor. Together they embark upon a harrowing cosmic adventure to uncover the mystery of the God Butcher’s vengeance and stop him before it’s too late.",
        "popularity": 12235.8,
        "poster_path": "/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg",
        "release_date": "2022-07-08",
        "title": "Thor: Love and Thunder",
        "video": false,
        "vote_average": 6.8,
        "vote_count": 1684
    }
    * */

    private int id;
    private String title;
    private String release_date;
    private String overview;
    private float vote_average;

    @SerializedName("backdrop_path")
    private  String image_path;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getRating() {
        return Math.round(getVote_average() * 10) + "%";
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", image_URL='" + image_path + '\'' +
                '}';
    }
}
