package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPatientActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this, null, null, 1);

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        setTitle("Edit Patient");

        EditText etEditName = (EditText) findViewById(R.id.etEditName);
        EditText etEditContact = (EditText) findViewById(R.id.etEditContact);
        EditText etEditAge = (EditText) findViewById(R.id.etEditAge);
        EditText etEditGender = (EditText) findViewById(R.id.etEditGender);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);

        String patientId = getIntent().getExtras().getString("patientId");
//        Toast.makeText(this, patientId, Toast.LENGTH_SHORT).show();
        Cursor response = dbHelper.getPatientById(patientId);

        while (response.moveToNext()) {
            etEditName.setText(response.getString(response.getColumnIndex("patientName")).toString());
            etEditContact.setText(response.getString(response.getColumnIndex("contact")).toString());
            etEditAge.setText(response.getString(response.getColumnIndex("age")).toString());
            etEditGender.setText(response.getString(response.getColumnIndex("gender")).toString());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.UpdatePatient(patientId, etEditName.getText().toString(), etEditAge.getText().toString(), etEditContact.getText().toString(), etEditGender.getText().toString());
                Intent intent = new Intent(EditPatientActivity.this, PatientListActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog.Builder adb = new AlertDialog.Builder(EditPatientActivity.this);
        adb.setMessage("Are you Sure?(Y/N)");
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.DeletePatient(patientId);
                Intent intent = new Intent(EditPatientActivity.this, PatientListActivity.class);
                startActivity(intent);
            }
        });
        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = adb.create();
                alert.show();
            }
        });

    }
}