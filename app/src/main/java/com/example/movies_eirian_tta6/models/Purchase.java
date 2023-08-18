package com.example.movies_eirian_tta6.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchase")
public class Purchase {
    @PrimaryKey()
    private int movie_id;
    private String movie_title;
    private int quantity;

    public Purchase(int movie_id, String movie_title, int quantity) {
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.quantity = quantity;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQtyByOne() {
        this.quantity+=1;
    }
    @Override
    public String toString() {
        return "Purchase{" +
                "movie_id='" + movie_id + '\'' +
                ", movie_title='" + movie_title + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
