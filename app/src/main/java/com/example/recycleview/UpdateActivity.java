package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText update_food_title_editText;
    Button update_button, delete_button;
    String id, foodTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_food_title_editText = findViewById(R.id.update_food_title_editText);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getSelectedData();

        update_button.setOnClickListener(view -> {
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
            foodTitle = update_food_title_editText.getText().toString();
            db.updateData(id, foodTitle);
        });

        delete_button.setOnClickListener(view -> {
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
            db.deleteData(id);
            finish();
        });
    }

    private void getSelectedData() {
        if (getIntent().hasExtra("foodId") && getIntent().hasExtra("foodTitle")){
            id = getIntent().getStringExtra("foodId");
            foodTitle = getIntent().getStringExtra("foodTitle");

            update_food_title_editText.setText(foodTitle);
        } else {
            Toast.makeText(this, "There's no food to update", Toast.LENGTH_SHORT).show();
        }
    }
}