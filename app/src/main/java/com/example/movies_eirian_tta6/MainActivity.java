package com.example.movies_eirian_tta6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.movies_eirian_tta6.databinding.ActivityMainBinding;
import com.example.movies_eirian_tta6.db.MyDatabase;

//https://api.themoviedb.org/3/movie/now_playing?api_key=235c5d1ec3d9dd00faa317d3a8f3c776&language=en-US&page=1&region=CA

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // view bindings
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setup the bottom navigation menu
        // - get a reference to the FragmentContainerView
        // - use the FragmentContainerView to retrieve a reference to the Navigation Controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // - Associate the bottom navigation view component with the navigation controller
        NavController navController = navHostFragment.getNavController();
        // - The Navigation Controller will automatically manage switching between fragments when
        // menu options are clicked
        NavigationUI.setupWithNavController(binding.bottomNavView,navController);



    }

    public void setScreenTitle(String str) {
        binding.tvScreenTitle.setText(str);
    }
}