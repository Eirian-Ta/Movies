package com.example.movies_eirian_tta6.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movies_eirian_tta6.MainActivity;
import com.example.movies_eirian_tta6.R;
import com.example.movies_eirian_tta6.adapter.MovieRVAdapter;
import com.example.movies_eirian_tta6.databinding.FragmentNowPlayingBinding;
import com.example.movies_eirian_tta6.db.MyDatabase;
import com.example.movies_eirian_tta6.listeners.OnMoviePurchaseClickListener;
import com.example.movies_eirian_tta6.models.ConfigurationResponseObject;
import com.example.movies_eirian_tta6.models.Movie;
import com.example.movies_eirian_tta6.models.MovieResponseObject;
import com.example.movies_eirian_tta6.models.Purchase;
import com.example.movies_eirian_tta6.network.API;
import com.example.movies_eirian_tta6.network.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlaying extends Fragment implements OnMoviePurchaseClickListener {
    private final String API_KEY = "235c5d1ec3d9dd00faa317d3a8f3c776";
    private final String LANGUAGE = "en-US";
    private final int PAGE = 1;
    private final String REGION = "CA";
    private String IMG_BASE_URL;

    private FragmentNowPlayingBinding binding;

    // data source for the RecyclerView
    private ArrayList<Movie> moviesArrayList = new ArrayList<>();
    private MovieRVAdapter movieAdapter;

    // instantiate the db
    private MyDatabase db;
    
    private API api;
    
    public NowPlaying() {
        super(R.layout.fragment_now_playing);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity)getActivity()).setScreenTitle(getString(R.string.now_playing_fragment_title));
        this.db = MyDatabase.getDatabase(((MainActivity)getActivity()));
        
        // when the app loads, get a reference to the API
        // - via the retrofitclient singleton
        this.api = RetrofitClient.getInstance().getApi();
        
        // using the API, connect to the endpoint and retrieve the data
        // - create a network request
        Call<ConfigurationResponseObject> requestToGetImgBaseUrl = this.api.getImageConfigurationInfo(API_KEY);
        // - execute the network request (synchronously or asynchronously)
        // - do it async
        requestToGetImgBaseUrl.enqueue(new Callback<ConfigurationResponseObject>() {
            @Override
            public void onResponse(Call<ConfigurationResponseObject> call, Response<ConfigurationResponseObject> response) {
                //Log.d("A4", "The request was successful");
                //Log.d("A4", "Response code: " + response.code());

                // error handling, in case something strange happens
                if (!response.isSuccessful()) {
                    Log.d("A4", "Failed getting img base url: " +  response.code());
                    return;
                }

                IMG_BASE_URL = response.body().getImageBaseUrl();
                //Log.d("A4-MainAct", IMG_BASE_URL);

                // -create the item adapter
                movieAdapter= new MovieRVAdapter(getActivity(), moviesArrayList, NowPlaying.this::onMoviePurchaseClicked, IMG_BASE_URL);
                // -associate the adapter with the recyclerview
                binding.rvMovies.setAdapter(NowPlaying.this.movieAdapter);
                // configure the recycler view
                binding.rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));

                //Lines between items
                NowPlaying.this.binding.rvMovies.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


                Call<MovieResponseObject> requestToGetNowPlayingMovies = NowPlaying.this.api.getAllMovies(API_KEY,LANGUAGE,PAGE,REGION);
                // - execute the network request (synchronously or asynchronously)
                // - do it async
                requestToGetNowPlayingMovies.enqueue(new Callback<MovieResponseObject>() {
                    @Override
                    public void onResponse(Call<MovieResponseObject> call, Response<MovieResponseObject> response) {
                        //Log.d("A4", "The request was successful");
                        //Log.d("A4", "Response code: " + response.code());

                        // error handling, in case something strange happens
                        if (!response.isSuccessful()) {
                            Log.d("A4", "Failed getting now playing movies: " +  response.code());
                            return;
                        }
                        List<Movie> moviesFromApi = response.body().getResults();
                        //Log.d("A4 - movies.size", String.valueOf(moviesFromApi.size()));
                        moviesArrayList.clear();
                        moviesArrayList.addAll(moviesFromApi);
                        movieAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MovieResponseObject> call, Throwable t) {
                        // - no internet
                        // - server down
                        Log.d("A4", "The request to get now playing movies failed");
                        Log.d("A4", t.getMessage());
                    }
                });
                
            }

            @Override
            public void onFailure(Call<ConfigurationResponseObject> call, Throwable t) {
                // - no internet
                // - server down
                Log.d("A4", "The request to get img base url failed");
                Log.d("A4", t.getMessage());
            }
        });


    }
    
    // This function is provided by the OnMoviePurchaseClickListener.java interface
    @Override
    public void onMoviePurchaseClicked(int movie_id, String movie_title) {
        // write the code that should be executed when the person clicks on the row

        Purchase p = db.purchaseDAO().getPurchaseByMovieId(movie_id);
        // If no previous ticket purchases exist, add the purchase to the database
        if (p == null) {
            Purchase aPurchase = new Purchase(movie_id,movie_title,1);
            db.purchaseDAO().insertPurchase(aPurchase);
        }
        // If a previous ticket purchase exists in database, update that purchase’s ticket quantity by 1
        else {
            p.increaseQtyByOne();
            db.purchaseDAO().updatePurchase(p);
        }


        Snackbar.make(binding.getRoot(), "Ticket purchased", Snackbar.LENGTH_SHORT).show();
    }
    
    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}