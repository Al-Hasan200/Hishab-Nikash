package com.example.hishabnikash;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //variables
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String CHECKEDITEM = "checked_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dark mode implement code here
        sharedPreferences = this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        //navigation drawer method call here
        drawerLayout();

        //back method call here
        back();
    }

    // set up the OnBackPressedCallback
    private void back(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations
                        = android.R.style.Animation_Dialog;
                dialog.setContentView(R.layout.exit_dialog);
                dialog.setCancelable(false);

                ImageButton no_btn = dialog.findViewById(R.id.no_btn);
                ImageButton yes_btn = dialog.findViewById(R.id.yes_btn);

                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.cancel();
                    }
                });

                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });

                dialog.show();
            }
        };
        // Add the callback to the OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    //navigation drawer method code here
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
                if (id == R.id.nav_home){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(id == R.id.nav_setting){
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    finish();
                }
                /*switch (id)
                {
                    case R.id.nav_home:

                        break;
                    case R.id.nav_setting:

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
                        return true;

                }*/
                return true;
            }
        });
    }

    //dark mode code here
    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }
}