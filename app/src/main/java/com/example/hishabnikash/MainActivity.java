package com.example.hishabnikash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //======== navigation drawer method call here ========
        drawerLayout();
    }

    //======== navigation drawer method code here ========
    public void drawerLayout(){
        ImageButton imageButton = findViewById(R.id.menu);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {
                    /*case R.id.nav_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_setting:
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                        break;
                    case R.id.nav_moreApps:
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                        break;
                    case R.id.nav_rateApp:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_shareApp:
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                        break;
                    case R.id.nav_termsCondition:
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                        break;
                    case R.id.nav_privacyPolicy:
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                        break;

                    default:
                        return true;*/

                }
                return true;
            }
        });
    }
}