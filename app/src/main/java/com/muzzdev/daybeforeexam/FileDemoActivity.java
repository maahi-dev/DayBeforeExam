package com.muzzdev.daybeforeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_demo);
        setTitle("File Demo");

        EditText etFileData = (EditText) findViewById(R.id.etFileData);
        Button btnAddDataToFile = (Button) findViewById(R.id.btnAddDataToFile);
        Button btnFetchDataFromFile = (Button) findViewById(R.id.btnFetchDataFromFile);
        TextView tvOutput = (TextView) findViewById(R.id.tvOutput);

        btnAddDataToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fileOutputStream;

                try {
                    fileOutputStream = openFileOutput("MyData", MODE_PRIVATE);
                    fileOutputStream.write(etFileData.getText().toString().getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnFetchDataFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fileInputStream;
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("MyData")));
                    String str;
                    while((str = bufferedReader.readLine())!=null){
                        stringBuilder.append(str);
                    }
                    tvOutput.setText(stringBuilder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}