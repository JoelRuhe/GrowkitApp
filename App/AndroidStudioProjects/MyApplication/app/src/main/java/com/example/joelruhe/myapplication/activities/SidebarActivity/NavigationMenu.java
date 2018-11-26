package com.example.joelruhe.myapplication.activities.SidebarActivity;

import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.joelruhe.myapplication.R;
import com.example.joelruhe.myapplication.activities.EnterPinActivity;
import com.example.joelruhe.myapplication.activities.FireBaseLoginActivity;
import com.example.joelruhe.myapplication.activities.LoginActivity;
import com.example.joelruhe.myapplication.activities.MainActivity;
import com.example.joelruhe.myapplication.activities.SplashScreenActivity;

public class NavigationMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new android.transition.Explode());
        setContentView(R.layout.navigation_menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_myplants){
        }
        else if (id == R.id.nav_mynetwork){
            Intent i = new Intent(NavigationMenu.this, FireBaseLoginActivity.class);
            startActivity(i,
                    ActivityOptions.makeSceneTransitionAnimation(NavigationMenu.this).toBundle());
        }
        else if (id == R.id.nav_settings){

        }
        else if (id == R.id.nav_logout){

        }
        return true;
    }
}
