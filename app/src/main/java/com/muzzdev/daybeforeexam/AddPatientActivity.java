package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatientActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        setTitle("Add Patient");

        EditText etAddPatientName = (EditText) findViewById(R.id.etAddPatientName);
        EditText etAddPatientContact = (EditText) findViewById(R.id.etAddPatientContact);
        EditText etAddPatientAge = (EditText) findViewById(R.id.etAddPatientAge);
        EditText etAddPatientGender = (EditText) findViewById(R.id.etAddPatientGender);
        Button btnAddPatient = (Button) findViewById(R.id.btnAddPatient);

        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.AddPatient(etAddPatientName.getText().toString(), etAddPatientAge.getText().toString(), etAddPatientContact.getText().toString(), etAddPatientGender.getText().toString());
                Toast.makeText(AddPatientActivity.this, "Record Insert Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddPatientActivity.this,PatientListActivity.class);
                startActivity(intent);
            }
        });

    }
}