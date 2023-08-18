package com.example.movies_eirian_tta6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_eirian_tta6.databinding.MovieRowLayoutBinding;
import com.example.movies_eirian_tta6.listeners.OnMoviePurchaseClickListener;
import com.example.movies_eirian_tta6.models.Movie;

import java.util.ArrayList;

public class MovieRVAdapter  extends RecyclerView.Adapter<MovieRVAdapter.MovieViewHolder>{
    private final Context context;
    private final ArrayList<Movie> moviesArrayList;
    MovieRowLayoutBinding binding;
    private final OnMoviePurchaseClickListener clickListener;
    private String img_base_url;

    public MovieRVAdapter(Context context, ArrayList<Movie> movies, OnMoviePurchaseClickListener clickListener, String img_base_url){
        this.moviesArrayList = movies;
        this.context = context;
        this.clickListener = clickListener;
        this.img_base_url = img_base_url;
    }

/*    public MovieRVAdapter(Context context, ArrayList<Movie> movies, OnMoviePurchaseClickListener clickListener){
        this.moviesArrayList = movies;
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setImg_url_base(String img_base_url) {
        this.img_base_url = img_base_url;
    }*/

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // retrieving the layout file for the recycler view's row
        // and creating a ViewHolder with it
        return new MovieViewHolder(MovieRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // getting a item from the data source and sending it to the ViewHolder
        Movie aMovie = moviesArrayList.get(position);
        holder.bind(context, aMovie, clickListener, img_base_url);
    }

    @Override
    public int getItemCount() {
        // returns the total number of items in the data source
        //Log.d("MyAdapter", "getItemCount: Number of items " +this.moviesArrayList.size() );
        return this.moviesArrayList.size();
    }

    // inner class
    // class within another class

    // drawing an individual row in the recycler
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        MovieRowLayoutBinding movieBinding;
        private final String FILE_SIZE = "w400";

        public MovieViewHolder(MovieRowLayoutBinding binding){
            super(binding.getRoot());
            this.movieBinding = binding;
        }

        // provides instructions for what data goes in which ui element of the row layout
        public void bind(Context context, Movie aMovie, OnMoviePurchaseClickListener clickListener, String img_base_url){
            // programmatically update the elements in the custom row layout here
            movieBinding.tvMovieTitle.setText(aMovie.getTitle());
            movieBinding.tvRating.setText(aMovie.getRating());
            movieBinding.tvReleaseDate.setText("Released: " + aMovie.getRelease_date());
            movieBinding.tvOverview.setText(aMovie.getOverview());
            movieBinding.tvMovieId.setText(String.valueOf(aMovie.getId()));

            // load the image
            //Log.d("A4-img url: ", img_base_url + FILE_SIZE + aMovie.getImage_path());
            Glide.with(context).load(img_base_url + FILE_SIZE + aMovie.getImage_path()).into(movieBinding.ivMovie);

            // click handler for the the button in each row
//            movieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickListener.onMoviePurchaseClicked(aMovie.getId());
//                }
//            });

            movieBinding.btnPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onMoviePurchaseClicked(aMovie.getId(),aMovie.getTitle());
                }
            });
        }
    }
}
