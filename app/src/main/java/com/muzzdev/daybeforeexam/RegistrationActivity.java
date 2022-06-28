package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("REGISTRATION");

        TextView tvalreadyhaveanaccount = (TextView) findViewById(R.id.tvalreadyhaveanaccount);
        EditText etRegName = (EditText) findViewById(R.id.etRegName);
        EditText etRegEmail = (EditText) findViewById(R.id.etRegEmail);
        EditText etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        Button btnRegistration = (Button) findViewById(R.id.btnRegistration);

        String[] specializations = {"Forensic Medicine", "Health Education", "Pharmacology", "Pathology", "Radiotherapy", "Radiodiagnosis"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, specializations);
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(adapter);

        tvalreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.Registration(etRegName.getText().toString(), autoCompleteTextView.getText().toString(), etRegEmail.getText().toString(), etRegPassword.getText().toString());
                Toast.makeText(RegistrationActivity.this, "Registration Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}