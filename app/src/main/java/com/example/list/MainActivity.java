package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity<adapterView> extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    ListView listeView1;
    ArrayAdapter myAdapter;
    EditText inputText1;
     Integer indexVal;
    String item;
    Button btnAdd,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.buttonAdd);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        inputText1 = findViewById(R.id.edit_text);
        listeView1 = findViewById(R.id.list);
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listeView1.setAdapter(myAdapter);

        // add items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val = inputText1.getText().toString();
                if (!val.isEmpty()) {
                    items.add(val);
                    myAdapter.notifyDataSetChanged();
                    inputText1.setText("");
                    Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "input is empty!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //select items and delete items
        listeView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String value = adapterView.getItemAtPosition(i).toString();
                item = adapterView.getItemAtPosition(i).toString() + " is selectes";
                 indexVal = i;
                listeView1.getChildAt(i).setBackgroundColor(Color.RED);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                inputText1.setText(value);

                //delete items

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        items.remove(i);
                        Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                        myAdapter.notifyDataSetChanged();
                        inputText1.setText("");
                    }
                });
            }
        });


        // update items
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val = inputText1.getText().toString();
                items.set(indexVal, val);

                if (!val.isEmpty()) {
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "data updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "input is empty!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}