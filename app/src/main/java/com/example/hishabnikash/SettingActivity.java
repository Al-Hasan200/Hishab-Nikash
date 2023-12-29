package com.example.hishabnikash;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingActivity extends AppCompatActivity {

    //variables
    private ImageView backArrow;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private final String CHECKEDITEM = "checked_item";
    private RelativeLayout darkLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //dark mode implement code here
        sharedPreferences = this.getSharedPreferences("theme", MODE_PRIVATE);
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

        darkLight = findViewById(R.id.darkLight);
        darkLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        //find variables id method call here
        findVariablesId();

        //back button click method call here
        backButtonClick();

        //back method call here
        back();
    }

    //find variables id method code here
    private void findVariablesId(){
        backArrow = findViewById(R.id.backArrow);
    }

    //back button click method code here
    private void backButtonClick(){
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    // set up the OnBackPressedCallback
    private void back(){
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
            }
        };
        // Add the callback to the OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    //dark mode dialog show method code here
    private void showDialog(){
        String[] theme = this.getResources().getStringArray(R.array.theme);

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this, R.style.myCheckedTextView);
        materialAlertDialogBuilder.setTitle("বাছাই করুন");
        materialAlertDialogBuilder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                selected = theme[i];
                checkedItem = i;
            }
        });

        materialAlertDialogBuilder.setPositiveButton("ওকে", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                try {
                    if (selected == null){
                        selected = theme[i];
                        checkedItem = i;
                    }
                }catch (Exception e){
                    Toast.makeText(SettingActivity.this, "বাছাই করা আছে", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                }

                switch (selected){
                    case "সিস্টেম ডিফল্ট":
                        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "ডার্ক মোড":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "লাইট মোড":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });

        materialAlertDialogBuilder.setNegativeButton("বাদ দিন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = materialAlertDialogBuilder.create();
        alertDialog.show();
    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }
}