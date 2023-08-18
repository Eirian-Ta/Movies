package com.example.movies_eirian_tta6.network;

import com.example.movies_eirian_tta6.models.ConfigurationResponseObject;
import com.example.movies_eirian_tta6.models.MovieResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// this defines the base url of the api
// it defines the endpoints we want to connect to
public interface API {
    // base url of the api
    public final String BASE_URL = "https://api.themoviedb.org/3/";

    // functions: endpoints that you want to connect to
    // - ACTIONS you are using to connect to the endpoint (GET, POST, PUT, PATCH, DELETE...)
    // - Data type that is returned by the endpoint

    // connect to get the configuration info
    // https://api.themoviedb.org/3/configuration?api_key=<api_key>
    @GET("configuration")
    public Call<ConfigurationResponseObject> getImageConfigurationInfo(@Query("api_key") String api_key);

    // connect to the get all items endpoint of the api
    //https://api.themoviedb.org/3/movie/now_playing?api_key=<api_key>>&language=en-US&page=1&region=CA
    @GET("movie/now_playing")
    public Call<MovieResponseObject>  getAllMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );
}
