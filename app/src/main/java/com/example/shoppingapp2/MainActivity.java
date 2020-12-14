package com.example.shoppingapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addItem;
    EditText korean, english;
    ListView totalItems;
    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItem = findViewById(R.id.addItem);
        korean = findViewById(R.id.input_korean);
        english = findViewById(R.id.input_english);
        totalItems = findViewById(R.id.totalItems);
        SQLiteDatabase shoppingApp;
        updateList();
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items item;
                try {
                    item = new Items(-1, korean.getText().toString(), english.getText().toString());

                }
                catch (Exception e){
                    item = new Items(-1, "에러", "error");

                }
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(item);
                Toast.makeText(MainActivity.this, "Success: " + success, Toast.LENGTH_SHORT).show();
                updateList();
            }
        });
        totalItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items clickedItem = (Items) parent.getItemAtPosition(position);
                databaseHelper.delOne(clickedItem);
                updateList();
                Toast.makeText(MainActivity.this, "Deleted " + clickedItem, Toast.LENGTH_SHORT).show();
            }
        });

        }

    private void updateList() {
        ArrayAdapter itemAdapter = new ArrayAdapter<Items>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, databaseHelper.getAllData());
        totalItems.setAdapter(itemAdapter);
    }
}

