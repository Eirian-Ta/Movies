package com.example.movies_eirian_tta6.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movies_eirian_tta6.models.Purchase;

import java.util.List;

// data access object
@Dao
public interface PurchaseDAO {

    //insert new row
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertPurchase(Purchase aPurchase);

    // retrieve data
    @Query("SELECT * FROM purchase")
    public List<Purchase> getAllPurchases();

    // retrieve one purchase
    @Query("SELECT * FROM purchase WHERE movie_id= :movieId")
    public Purchase getPurchaseByMovieId(int movieId);

    // update a purchase
    @Update
    public void updatePurchase(Purchase purchaseToUpdate);

    // delete a purchase
    @Delete
    public void deletePurchase(Purchase purchaseToDelete);
}
