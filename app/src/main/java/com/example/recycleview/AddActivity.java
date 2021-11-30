package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText food_title_editText;
    Button add_food_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        food_title_editText = findViewById(R.id.food_title_editText);
        add_food_button = findViewById(R.id.add_food_button);

        add_food_button.setOnClickListener(view -> {
            DatabaseHelper db = new DatabaseHelper(AddActivity.this);
            db.insertFood(food_title_editText.getText().toString());
        });
    }
}