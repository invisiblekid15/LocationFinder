package com.chakhle.www.locationtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class food extends Activity {
    protected EditText food;
    protected Button save;
    protected Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.food_sold);
        food = (EditText) findViewById(R.id.food);
        save = (Button) findViewById(R.id.saveFood);
        exit = (Button) findViewById(R.id.finish);

        writeToFile out = new writeToFile();
        out.write("Food :\n");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFoodItem();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main;
                main = new Intent(food.getContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
    private void saveFoodItem()
    {
        if(food.getText().toString().trim().equals(""))
        {
            new AlertDialog.Builder(this).setTitle("Enter the food item first.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
        else {
            writeToFile out = new writeToFile();
            out.write(food.getText().toString() + "+");
            Toast.makeText(this, "Item saved",Toast.LENGTH_LONG).show();
            food.setText("");
        }
    }
}
