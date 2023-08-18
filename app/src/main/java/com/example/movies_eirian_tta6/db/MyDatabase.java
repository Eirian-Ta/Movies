package com.example.movies_eirian_tta6.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movies_eirian_tta6.models.Purchase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Create a database named "mydb", with a table called Employee
// The definition for the Employee table is in the Employee.java file
@Database(entities = {Purchase.class}, version=1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

     // TODO: List your DAOs here
    public abstract PurchaseDAO purchaseDAO();

     private static volatile MyDatabase INSTANCE;
     private static final int NUMBER_OF_THREADS = 4;
     static final ExecutorService databaseWriteExecutor =
             Executors.newFixedThreadPool(NUMBER_OF_THREADS);

     public static MyDatabase getDatabase(final Context context) {
         if (INSTANCE == null) {
             synchronized (MyDatabase.class) {
                 if (INSTANCE == null) {
                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                     MyDatabase.class, "movie_ticket_database")
                             .allowMainThreadQueries()
                             .fallbackToDestructiveMigration()
                             .build();
                 }
             }
         }
         return INSTANCE;
     }
}

