package it.sal.disco.unimib.filmreviewer.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.sal.disco.unimib.filmreviewer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Required for navigation between fragment
        NavHostFragment navHostFragment_news_act =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.main_fragmentContainerView);
        NavController navController_news_act =
                navHostFragment_news_act.getNavController();
        BottomNavigationView bottomNavigView_news_act =
                findViewById(R.id.main_bottom_nav_view);
        NavigationUI
                .setupWithNavController(bottomNavigView_news_act, navController_news_act);

    }
}