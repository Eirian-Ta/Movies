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
import com.example.movies_eirian_tta6.adapter.PurchasedTicketRVAdapter;
import com.example.movies_eirian_tta6.databinding.FragmentMyTicketsBinding;
import com.example.movies_eirian_tta6.db.MyDatabase;
import com.example.movies_eirian_tta6.listeners.OnPurchasedTicketRowClickListener;
import com.example.movies_eirian_tta6.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MyTickets extends Fragment implements OnPurchasedTicketRowClickListener {
    private FragmentMyTicketsBinding binding;

    // data source for the RecyclerView
    private ArrayList<Purchase> ticketsArrayList = new ArrayList<>();
    private PurchasedTicketRVAdapter purchaseAdapter;

    // instantiate the db
    private MyDatabase db;

    public MyTickets() {
        super(R.layout.fragment_my_tickets);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("A4", "My Tickets Screen");

        ((MainActivity)getActivity()).setScreenTitle(getString(R.string.my_tickets_fragment_title));
        this.db = MyDatabase.getDatabase(getActivity());
        ticketsArrayList = new ArrayList<>(db.purchaseDAO().getAllPurchases());

        if (ticketsArrayList.size()>0) {
            binding.tvNoTicketMessage.setVisibility(View.INVISIBLE);
        }

        // -create the item adapter
        purchaseAdapter= new PurchasedTicketRVAdapter(getActivity(), ticketsArrayList, this::onTicketRowClicked);
        // -associate the adapter with the recyclerview
        binding.rvTickets.setAdapter(this.purchaseAdapter);
        // configure the recycler view
        binding.rvTickets.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Lines between items
        this.binding.rvTickets.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));




    }

    // This function is provided by the OnPurchasedTicketRowClickedListener.java interface
    @Override
    public void onTicketRowClicked(int movie_id) {
        // write the code that should be executed when the person clicks on the row
        // (or something in the row)
        Purchase p = db.purchaseDAO().getPurchaseByMovieId(movie_id);
        db.purchaseDAO().deletePurchase(p);
        updateListView();
        Snackbar.make(binding.getRoot(), "Deleted!", Snackbar.LENGTH_SHORT).show();
    }

    public void updateListView() {
        // - update the rv
        // - get al tickets
        List<Purchase> ticketsList = db.purchaseDAO().getAllPurchases();

        if (ticketsList.size()>0) {
            binding.tvNoTicketMessage.setVisibility(View.INVISIBLE);
        }
        else {
            binding.tvNoTicketMessage.setVisibility(View.VISIBLE);
        }
        ticketsArrayList.clear();
        ticketsArrayList.addAll(ticketsList);
        purchaseAdapter.notifyDataSetChanged();
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentMyTicketsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}