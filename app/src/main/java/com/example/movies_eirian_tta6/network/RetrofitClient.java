package com.example.movies_eirian_tta6.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // the api interface
    private API api;

    private RetrofitClient() {
        // create an instance of the parser
        Gson gson = new GsonBuilder().create();

        // create an instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // create a concrete instance of the api interface
        this.api = retrofit.create(API.class);
    }

    // getter for returning the api


    public API getApi() {
        return api;
    }

    // turn this class into a singleton
    private static RetrofitClient instance = null;
    public static synchronized RetrofitClient getInstance(){
        if ( instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }
}
