package com.example.recycleview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView my_recycler_view;
    FloatingActionButton add_button;

    DatabaseHelper db;
    Adapter adapter;

    ArrayList<String> foodTitle, foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_recycler_view = findViewById(R.id.my_recycler_view);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        db = new DatabaseHelper(MainActivity.this);
        foodTitle = new ArrayList<>();
        foodId = new ArrayList<>();

        getDataFromDataBase();

        adapter = new Adapter(MainActivity.this, MainActivity.this,foodId ,foodTitle);
        my_recycler_view.setAdapter(adapter);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void getDataFromDataBase(){
        Cursor cursor = db.getAllFoods();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "There's no food", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                foodId.add(cursor.getString(0));
                foodTitle.add(cursor.getString(1));
            }
        }
    }
}