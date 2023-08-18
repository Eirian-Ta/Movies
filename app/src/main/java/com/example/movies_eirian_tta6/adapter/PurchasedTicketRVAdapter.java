package com.example.movies_eirian_tta6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_eirian_tta6.databinding.PurchaseRowLayoutBinding;
import com.example.movies_eirian_tta6.listeners.OnPurchasedTicketRowClickListener;
import com.example.movies_eirian_tta6.models.Purchase;

import java.util.ArrayList;

public class PurchasedTicketRVAdapter extends RecyclerView.Adapter<PurchasedTicketRVAdapter.PurchasedTicketViewHolder>{
    private final Context context;
    private final ArrayList<Purchase> ticketsArrayList;
    PurchaseRowLayoutBinding binding;
    private final OnPurchasedTicketRowClickListener clickListener;

    public PurchasedTicketRVAdapter(Context context, ArrayList<Purchase> purchases, OnPurchasedTicketRowClickListener clickListener){
        this.ticketsArrayList = purchases;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PurchasedTicketRVAdapter.PurchasedTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // retrieving the layout file for the recycler view's row
        // and creating a ViewHolder with it
        return new PurchasedTicketRVAdapter.PurchasedTicketViewHolder(PurchaseRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedTicketRVAdapter.PurchasedTicketViewHolder holder, int position) {
        // getting a item from the data source and sending it to the ViewHolder
        Purchase purchasedTicket = ticketsArrayList.get(position);
        holder.bind(context, purchasedTicket, clickListener);
    }

    @Override
    public int getItemCount() {
        // returns the total number of items in the data source
        //Log.d("MyAdapter", "getItemCount: Number of items " +this.ticketsArrayList.size() );
        return this.ticketsArrayList.size();
    }

    // inner class
    // class within another class

    // drawing an individual row in the recycler
    public static class PurchasedTicketViewHolder extends RecyclerView.ViewHolder{
       PurchaseRowLayoutBinding purchaseBinding;

        public PurchasedTicketViewHolder(PurchaseRowLayoutBinding binding){
            super(binding.getRoot());
            this.purchaseBinding = binding;
        }

        // provides instructions for what data goes in which ui element of the row layout
        public void bind(Context context, Purchase purchasedTicket, OnPurchasedTicketRowClickListener clickListener){
            // TODO:programatically update the elements in the custom row layout here
            purchaseBinding.tvMovieTitle.setText(purchasedTicket.getMovie_title());
            purchaseBinding.tvNumOfTicket.setText("Tickets Purchased:" + String.valueOf(purchasedTicket.getQuantity()));
//            purchaseBinding.tvRating.setText(aMovie.getRating());
//            purchaseBinding.tvReleaseDate.setText("Released: " + aMovie.getRelease_date());
//            purchaseBinding.tvOverview.setText(aMovie.getOverview());
//            purchaseBinding.tvMovieId.setText(String.valueOf(aMovie.getId()));

            // click handler for each row
            purchaseBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onTicketRowClicked(purchasedTicket.getMovie_id());
                }
            });
            
        }
    }
}
