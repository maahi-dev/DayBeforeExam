package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class PatientListActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this, null, null, 1);
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> listIds = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        setTitle("Patient List");

        ListView patientList = (ListView) findViewById(R.id.patientList);

        //Getting Values from Database
        Cursor getAllPatient = dbHelper.GetAllPatient();
        if (getAllPatient != null) {
            getAllPatient.moveToFirst();
            do{
                list.add(getAllPatient.getString(getAllPatient.getColumnIndex("patientName")));
                listIds.add(getAllPatient.getString(getAllPatient.getColumnIndex("patientId")));
            }while(getAllPatient.moveToNext());

            adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
            patientList.setAdapter(adapter);
        }

        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(PatientListActivity.this, listIds.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PatientListActivity.this,EditPatientActivity.class);
                intent.putExtra("patientId",listIds.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {
                adapter.getFilter().filter(search);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}