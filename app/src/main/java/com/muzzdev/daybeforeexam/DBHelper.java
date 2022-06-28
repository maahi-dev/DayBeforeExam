package com.muzzdev.daybeforeexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ClinicDB", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Doctor(doctorId integer primary key autoincrement,doctorName text,specialization text,email text,password text)");
        db.execSQL("create table Patient(patientId integer primary key autoincrement,patientName text,age text,contact text,gender text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void Registration(String doctorName, String specialization, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("doctorName", doctorName);
        cv.put("specialization", specialization);
        cv.put("email", email);
        cv.put("password", password);

        sqLiteDatabase.insert("Doctor", null, cv);
    }

    public boolean Login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor response = sqLiteDatabase.rawQuery("select * from Doctor where email = ? and password=?", new String[]{email, password});
        if (response.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void AddPatient(String patientName, String age, String contact, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("patientName", patientName);
        cv.put("age", age);
        cv.put("contact", contact);
        cv.put("gender", gender);

        sqLiteDatabase.insert("Patient", null, cv);
    }

    public Cursor GetAllPatient() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor response = sqLiteDatabase.rawQuery("select * from Patient", null);
        return response;
    }

    public Cursor getPatientById(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor response = sqLiteDatabase.rawQuery("select * from Patient where patientId=?", new String[]{id});
        return response;
    }

    public void UpdatePatient(String patientId, String patientName, String age, String contact, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("patientName", patientName);
        cv.put("age", age);
        cv.put("contact", contact);
        cv.put("gender", gender);
        sqLiteDatabase.update("Patient", cv, "patientId=?", new String[]{patientId});
    }

    public void DeletePatient(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Patient","patientId=?",new String[]{id});
    }
}
