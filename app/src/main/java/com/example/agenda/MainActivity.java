package com.example.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.agenda.Adapters.CustomAdapter;
import com.example.agenda.Open_Helper.OpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    OpenHelper myDB;
    ArrayList<String>  user_id, user_name, user_last, user_phone, user_category, user_date, user_time;

    //CustomAdapter customAdapter;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initXML();

        createDataBase();
        storeDataInArrays();



    }
    public void initXML(){
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(this);

        myDB = new OpenHelper(MainActivity.this);

        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_last = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_category = new ArrayList<>();
        user_date = new ArrayList<>();
        user_time = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        if(view == add_button){
            goAddUser();
        }
    }

    private void goAddUser() {
        Intent i = new Intent(MainActivity.this, ActivityAdd.class);
        startActivity(i);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                //Toast.makeText(this, "id: " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_last.add(cursor.getString(2));
                user_phone.add(cursor.getString(3));
                user_category.add(cursor.getString(4));
                user_date.add(cursor.getString(5));
                user_time.add(cursor.getString(6));
            }
            customAdapter = new CustomAdapter(MainActivity.this,this, user_id, user_name, user_last,
                    user_phone,user_category, user_date, user_time);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }

    public void createDataBase(){
        myDB = new OpenHelper(MainActivity.this);
        SQLiteDatabase db = myDB.getWritableDatabase();
        if (db != null){
            //Toast.makeText(this, "BD CREADA CON EXITO!", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

}
