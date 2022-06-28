package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("LOGIN");

        //Declarations
        TextView tvdonthaveaccount = (TextView) findViewById(R.id.tvdonthaveaccount);
        EditText etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
        EditText etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        //Checking If Already Login or not
        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        if (preferences.contains("isLogin")) {
            if (preferences.getBoolean("isLogin", false) == true) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }

        tvdonthaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isLogin = dbHelper.Login(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
                if (isLogin) {

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("username", etLoginUsername.getText().toString());
                    editor.putBoolean("isLogin", true);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}