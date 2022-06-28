package com.muzzdev.daybeforeexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Day Before Exam");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.AddPatient:
                Intent intent1 = new Intent(HomeActivity.this,AddPatientActivity.class);
                startActivity(intent1);
                return true;
            case R.id.ListPatient:
                Intent intent2 = new Intent(HomeActivity.this,PatientListActivity.class);
                startActivity(intent2);
                return true;
            case R.id.FileDemo:
                Intent intent4 = new Intent(HomeActivity.this,FileDemoActivity.class);
                startActivity(intent4);
                return true;
            case R.id.Logout:
                SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("username","");
                editor.putBoolean("isLogin",false);
                editor.commit();
                Intent intent3 = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}